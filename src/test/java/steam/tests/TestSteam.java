package steam.tests;

import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import steam.pageObjects.forms.*;
import steam.pageObjects.menus.MenuGames;


public class TestSteam extends BaseTest {

    @Test
    public void runTest() {

        MainPage mainPage = new MainPage();
        mainPage.menuLanguage.setLanguage();
        mainPage.menuGames.selectGenre(MenuGames.MenuEnum.GAME, MenuGames.GameEnum.ACTION);

        ActionPage actionPage = new ActionPage();
        actionPage.goToSpecials();

        SpecialsPage specialsPage = new SpecialsPage();
        specialsPage.findGame();

        GamePage gamePage = new GamePage();
        Assert.assertTrue(gamePage.isTrueValue());

        gamePage.navigateToDownload();

        DownloadPage downloadPage = new DownloadPage();
        downloadPage.downloadSteam();
        Assert.assertTrue(downloadPage.checkDownloadFile());

    }
}

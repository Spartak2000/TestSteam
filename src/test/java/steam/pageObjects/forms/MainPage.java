package steam.pageObjects.forms;

import framework.BasePage;
import org.openqa.selenium.By;
import steam.pageObjects.menus.MenuGames;
import steam.pageObjects.menus.MenuLanguage;

public class MainPage extends BasePage {

    public MenuLanguage menuLanguage = new MenuLanguage();
    public MenuGames menuGames = new MenuGames();
    private String locatorUnique="//img[@class='home_page_gutter_giftcard']";


    public MainPage() {
        locatorPage = By.xpath(locatorUnique);
        isOpen(locatorPage);
    }


}

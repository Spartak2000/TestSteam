package steam.pageObjects.menus;

import framework.BaseEntity;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class MenuGames extends BaseEntity {

    private String listLocator = "//span[@class='pulldown']//a[text()='%s']";
    private String genreLocator = "//div[@class='popup_body popup_menu']//a[contains(text(), '%s')]";

    public enum MenuEnum {
        GAME("Games"),
        SOFTWARE("Software");

        private String partLocator;

        MenuEnum(String str) {
            this.partLocator = str;
        }
    }

    public enum GameEnum {
        ACTION("Action"),
        SPORTS("Sports");

        private String partLocator;

        GameEnum(String str) {
            this.partLocator = str;
        }
    }


    public void selectGenre(MenuEnum menuEnum, GameEnum gameEnum) {
        Label lblGame = new Label(By.xpath(String.format(listLocator, langReader.getProperties(menuEnum.partLocator))));
        lblGame.moveTo();

        Label lblAction = new Label(By.xpath(String.format(genreLocator, langReader.getProperties(gameEnum.partLocator))));
        lblAction.moveTo();
        lblAction.click();


    }

}

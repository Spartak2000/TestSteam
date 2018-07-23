package steam.pageObjects.menus;

import framework.BaseEntity;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MenuLanguage extends BaseEntity {
    private Button btnLanguage;
    private String listLocator = "//div[@class='popup_body popup_menu']//a[text()='%s']";
    private String locatorLanguage="//span[@id='language_pulldown']";

    private enum LangEnum {
        RU("Русский (Russian)"),
        EN("English (английский)");

        private String partLocator;

        LangEnum(String str) {
            this.partLocator = str;
        }

    }

    public void setLanguage() {
        if (!checkToChangeLanguage()) {
            btnLanguage.click();
            Label lblMenuLanguage = new Label(By.xpath(String.format(listLocator, LangEnum.valueOf(configReader.getProperties("lang").toUpperCase()).partLocator)));
            lblMenuLanguage.click();
        }
    }

    private boolean checkToChangeLanguage() {
        btnLanguage = new Button(By.xpath(locatorLanguage));
        return btnLanguage.getText().equals(langReader.getProperties("lang"));
    }
}

package steam.pageObjects.forms;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class ActionPage extends BasePage {
    private String locatorSpecial = "//span[contains(text(), '%s')]";
    private String locatorUnique="//h2[contains(text(), '%s')]";


    public ActionPage() {
        locatorPage = By.xpath(String.format(locatorUnique, langReader.getProperties("locatorAction")));
        isOpen(locatorPage);
    }

    public void goToSpecials() {
        Button btnSpecial = new Button(By.xpath(String.format(locatorSpecial, langReader.getProperties("Special"))));
        btnSpecial.click();
    }

}

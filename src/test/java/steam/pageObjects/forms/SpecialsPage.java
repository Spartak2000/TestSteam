package steam.pageObjects.forms;

import framework.BasePage;
import framework.BaseElement;
import framework.elements.Button;
import org.openqa.selenium.By;


public class SpecialsPage extends BasePage {
    private String locatorGame = "//span[contains(text(),'-%s')]";
    private String locatorSalary = "//span[contains(text(),'-%s')]/../..//div[@class='col search_price discounted responsive_secondrow']";
    private String locatorSpecial = "//div[@class='col search_discount responsive_secondrow']";
    private String locatorUnique="//h2[contains(text(),'%s')]";
    public static String salarySelectGame;
    public static String salarySelectGameWithSpecial;


    public SpecialsPage() {
        locatorPage = By.xpath(String.format(locatorUnique, langReader.getProperties("locatorSpecial")));
        isOpen(locatorPage);
    }

    public void findGame() {
        Button btnGame = new Button(By.xpath(String.format(locatorGame, getMaxSpecial())));
        Button salaryGame = new Button(By.xpath(String.format(locatorSalary, getMaxSpecial())));
        String[] partsSalary = salaryGame.getText().split("\n");
        salarySelectGame = partsSalary[0];
        salarySelectGameWithSpecial = partsSalary[1];
        btnGame.click();
    }

    private String getMaxSpecial() {
        return BaseElement.getCollection(locatorSpecial);
    }

}

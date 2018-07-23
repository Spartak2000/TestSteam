package steam.pageObjects.forms;

import framework.BaseElement;
import framework.BasePage;
import framework.elements.Button;
import framework.elements.ListElement;
import org.openqa.selenium.By;

public class GamePage extends BasePage {
    private String locatorSalary = "//div[@class='discount_prices']";
    private String locatorDownload = "//a[@class='header_installsteam_btn_content']";
    private String locatorCheckGame = "//a[@class='btn_grey_white_innerfade btn_medium']";
    private String locatorCheckGameWithYear = "//a[@class='btnv6_blue_hoverfade btn_small']";
    private String locatorYear = "//select[@name='ageYear']";
    private String strYear = "1998";
    private String locatorUnique = "//a[@class='btnv6_green_white_innerfade btn_medium']";
    private String locatorGamePage = "//a[@class='btnv6_green_white_innerfade btn_medium']";

    public GamePage() {
        isOpenWindow();
        locatorPage = By.xpath(locatorUnique);
        isOpen(locatorPage);
    }

    private void isOpenWindow() {
        if (!BaseElement.isFound(locatorGamePage)) {
            if (BaseElement.isFound(locatorCheckGame)) {
                Button btnCheckGame = new Button(By.xpath(locatorCheckGame));
                btnCheckGame.click();
            }
            if (BaseElement.isFound(locatorCheckGameWithYear)) {
                ListElement listCheckGame = new ListElement(By.xpath(locatorYear));
                listCheckGame.selectElement(strYear);
                Button btnCheckGame = new Button(By.xpath(locatorCheckGameWithYear));
                btnCheckGame.click();
            }
        }
    }

    public boolean isTrueValue() {
        isOpenWindow();
        Button btnSalary = new Button(By.xpath(locatorSalary));
        String[] partsSalary = btnSalary.getText().split("\n");
        return partsSalary[0].equals(SpecialsPage.salarySelectGame) && partsSalary[1].split(" ")[0].equals(SpecialsPage.salarySelectGameWithSpecial);
    }

    public void navigateToDownload() {
        Button btnDownload = new Button(By.xpath(locatorDownload));
        btnDownload.click();
    }
}

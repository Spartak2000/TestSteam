package framework;

import framework.BaseEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class BaseElement extends BaseEntity {

    private By uniqueLocatorElement;

    protected WebElement webElement;

    protected BaseElement(By locatorElement) {
        webElement = driver.findElement(locatorElement);
        uniqueLocatorElement = locatorElement;
    }

    public void click() {
        if (webElement.isEnabled())
            webElement.click();
    }

    public String getText() {
        if (webElement.isEnabled())
            return webElement.getText();
        return "";
    }

    public boolean isEnabled() {
        List<WebElement> list = driver.findElements(uniqueLocatorElement);
        return list.size() > 0;
    }


    public void moveTo() {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.webElement).build().perform();
    }

    public static String getCollection(String locatorSpecial) {
        List<WebElement> webElementList = driver.findElements(By.xpath(locatorSpecial));
        int maxSpecial = 0;
        for (int i = 0; i < webElementList.size(); i++) {
            if (maxSpecial < Integer.parseInt(webElementList.get(i).getText().substring(1, webElementList.get(i).getText().length()).substring(0, webElementList.get(i).getText().length() - 2)))
                maxSpecial = Integer.parseInt(webElementList.get(i).getText().substring(1, webElementList.get(i).getText().length()).substring(0, webElementList.get(i).getText().length() - 2));
        }
        return String.valueOf(maxSpecial);
    }

    public static boolean isFound(String locator) {
        return !driver.findElements(By.xpath(locator)).isEmpty();
    }

}
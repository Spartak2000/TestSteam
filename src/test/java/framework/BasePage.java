package framework;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;


public abstract class BasePage extends BaseEntity {
    protected By locatorPage;



    protected void isOpen(By locator) {
        Label uniqueElement = new Label(locator);
        Assert.assertTrue(uniqueElement.isEnabled());
    }

}


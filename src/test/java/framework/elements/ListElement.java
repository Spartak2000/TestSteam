package framework.elements;

import framework.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ListElement extends BaseElement {
    public ListElement(By locatorElement) {
        super(locatorElement);
    }

    public void selectElement(String strYear) {
        Select selectAim = new Select(webElement);
        selectAim.selectByValue(strYear);
    }
}

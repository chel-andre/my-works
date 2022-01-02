package pages;

import driver.DriverManager;
import enums.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, 30);
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected WebElement getElement(By element) {
        return driver.findElement(element);
    }

    protected List<WebElement> getElements(By element) {
        return driver.findElements(element);
    }

    protected void click(By element) {
        getElement(element).click();
    }

    protected void clearField(By element) {
        getElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    protected String getAttribute(By element, Attribute attribute) {
        return getElement(element).getAttribute(attribute.getValue());
    }
}
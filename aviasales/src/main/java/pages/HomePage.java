package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomePage extends BasePage {
    private final By cityFrom = By.id("origin");
    private final By destination = By.id("destination");
    private final By calendar = By.xpath("//div[@data-test-id='departure-date-field']");
    private final By passengers = By.xpath("//div[@data-test-id='passengers-field']");
    private final By addChildren = By.xpath("//div[@data-test-id='passengers-children-field']//a[contains(@class,'increment')]");
    private final By searchBtn = By.xpath("//button[@data-test-id='form-submit']");
    private final By showBtn = By.xpath("//div[@class='prediction-advice__title']");

    public HomePage openPage() {
        open(PropertyReader.getUrl());
        return this;
    }

    public HomePage enterCityFrom(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(cityFrom));
        clearField(cityFrom);
        getElement(cityFrom).sendKeys(city);
        return this;
    }

    public HomePage enterDestinationCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(destination));
        getElement(destination).sendKeys(city);
        return this;
    }

    public HomePage selectPassengers() {
        click(passengers);
        click(addChildren);
        return this;
    }

    public HomePage clickCalendar() {
        wait.until(ExpectedConditions.elementToBeClickable(calendar));
        click(calendar);
        return this;
    }

    public HomePage selectDays(String plusDay) {
        getElement(By.xpath(String.format("//div[@class='calendar__months']//div[contains(text(),'%s')]", addDays(plusDay)))).click();
        return this;
    }

    public HomePage clickSearchBtn() {
        click(searchBtn);
        switchToTab();
        return this;
    }

    private String addDays(String plusDay) {
        Calendar calendar = new GregorianCalendar();
        int today = calendar.get(Calendar.DATE);
        int newDay = today + Integer.parseInt(plusDay);
        return Integer.toString(newDay);
    }

    private void switchToTab() {
        ArrayList<String> tab2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tab2.get(0));
        driver.close();
        driver.switchTo().window(tab2.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(showBtn));
    }
}
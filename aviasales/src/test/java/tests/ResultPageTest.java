package tests;

import driver.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;
import testData.TestData;

public class ResultPageTest extends BaseTest {

    @BeforeClass
    public void precondition() {
        new HomePage().openPage()
                .enterCityFrom(TestData.CITY_FROM)
                .enterDestinationCity(TestData.CITY_DESTINATION)
                .selectPassengers()
                .clickCalendar()
                .selectDays(TestData.TOMORROW)
                .selectDays(TestData.DAY_AFTER_TOMORROW)
                .clickSearchBtn();
    }

    @Test
    public void checkingResultPage() {
        new ResultPage().verifyCityFrom()
                .verifyCityDestination()
                .verifySortPrice()
                .verifyDepartureData();
    }
}
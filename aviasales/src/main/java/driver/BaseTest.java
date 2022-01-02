package driver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setupDriver() {
        DriverManager.setDriver();
    }

    @AfterClass
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
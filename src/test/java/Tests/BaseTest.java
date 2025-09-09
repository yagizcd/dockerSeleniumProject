package Tests;

import keywords.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;


    @BeforeTest
    public void setUp() {
        DriverFactory.getDriver();
    }

    @AfterTest
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}

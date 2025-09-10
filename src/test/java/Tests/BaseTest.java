package Tests;

import keywords.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;

    @Parameters({"browserName"})
    @BeforeTest
    public void setUp(@Optional String browserName) {
        DriverFactory.initDriver(browserName);
    }

    @AfterTest
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}

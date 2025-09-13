// src/test/java/Tests/BaseTest.java

package Tests;

import org.testng.annotations.*;
import keywords.DriverFactory;
import utils.ExtentManager;

public abstract class BaseTest {


    @Parameters("browserName")
    @BeforeMethod
    public void setUp(@Optional String browserName) {
        DriverFactory.initDriver(browserName);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentManager.flush();
    }
}
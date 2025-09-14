// src/test/java/Tests/BaseTest.java

package Tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;
import keywords.DriverFactory;
import utils.ExtentManager;

import java.lang.reflect.Method;

public abstract class BaseTest {


    @Parameters("browserName")
    @BeforeMethod
    public void setUp(@Optional String browserName, Method method) {
        ThreadContext.put("testname", method.getName());
        DriverFactory.initDriver(browserName);

    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        ThreadContext.clearMap();
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentManager.flush();
    }
}
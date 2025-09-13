package keywords;

import base.AppConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private DriverFactory(){

    }


    public static synchronized void initDriver(String browserName){
        if (driverThreadLocal.get() == null) {
            WebDriver driver;

            if (browserName == null) {
                driver = new ChromeDriver();
            } else {

                if (browserName.equalsIgnoreCase("chrome")) {
                    driver = new ChromeDriver();
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    driver = new FirefoxDriver();
                } else {
                    System.out.println("There is no such defined browser system will move on with default browser");

                    if (AppConstants.browserName.equalsIgnoreCase("chrome")) {
                        driver = new ChromeDriver();
                    } else if (AppConstants.browserName.equalsIgnoreCase("firefox")) {
                        driver = new FirefoxDriver();
                    } else {
                        System.out.println("there is not default driver defined in system tests will execute on chrome driver");
                        driver = new ChromeDriver();
                    }
                }

            }
            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }

    }

    public static synchronized WebDriver getDriver(){
        if (driverThreadLocal.get()==null){
            throw new IllegalStateException("Driver not yet initialied please initialize browser first");
        }
        else {
            return driverThreadLocal.get();
        }
    }

    public static void quitDriver(){
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}

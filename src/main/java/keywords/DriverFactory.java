package keywords;

import base.AppConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private DriverFactory(){

    }

    public static synchronized WebDriver getDriver(){
        if (driverThreadLocal.get() == null){
            String browser = AppConstants.browserName;
            WebDriver driver = null;
            if(browser.equalsIgnoreCase("chrome")){
                if(AppConstants.platformName.equalsIgnoreCase("local")) {
                    driver = new ChromeDriver();
                }
            }
            else if(browser.equalsIgnoreCase("firefox")){
                if(AppConstants.platformName.equalsIgnoreCase("local")) {
                    driver = new FirefoxDriver();
                }
            }

            else {
                System.out.println("Browser name entered is not supported!!");
            }
            driver.manage().window().maximize();

            driverThreadLocal.set(driver);
        }

        return driverThreadLocal.get();
    }

    public static void quitDriver(){
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}

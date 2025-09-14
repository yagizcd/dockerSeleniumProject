package keywords;

import base.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private DriverFactory(){

    }


    public static synchronized void initDriver(String browserName){
        if (driverThreadLocal.get() == null) {
            WebDriver driver = null;
            ChromeOptions co;
            FirefoxOptions fo;

            if (browserName == null) {
                driver = new ChromeDriver();
            } else {

                if (browserName.equalsIgnoreCase("chrome")) {
                    if (AppConstants.platformName.equalsIgnoreCase("local")){
                        driver = new ChromeDriver();
                    }
                    else if (AppConstants.platformName.equalsIgnoreCase("remote")){
                        co = new ChromeOptions();
                        co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                        co.setPlatformName("linux");

                        try {
                            driver = new RemoteWebDriver(new URL("http://localhost:4444"), co);
                        }catch (MalformedURLException  e){
                            throw new RuntimeException(e);
                        }
                    }

                    else{
                        logger.error("Platform not supported!");
                        throw new IllegalArgumentException("Desteklenmeyen platform adı: " + AppConstants.platformName);

                    }

                } else if (browserName.equalsIgnoreCase("firefox")) {
                    if (AppConstants.platformName.equalsIgnoreCase("local")) {
                        driver = new FirefoxDriver();
                    }
                    else if (AppConstants.platformName.equalsIgnoreCase("remote")){
                        fo = new FirefoxOptions();
                        fo.setPageLoadStrategy(PageLoadStrategy.EAGER);
                        fo.setPlatformName("linux");

                        try {
                            driver = new RemoteWebDriver(new URL("http://localhost:4444"), fo);
                        }catch (MalformedURLException  e){
                            throw new RuntimeException(e);
                        }
                    }
                    else{
                        logger.error("Platform not supported!");
                        throw new IllegalArgumentException("Desteklenmeyen platform adı: " + AppConstants.platformName);
                    }

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

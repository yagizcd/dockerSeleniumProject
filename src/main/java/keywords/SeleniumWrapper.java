package keywords;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ExtentManager;

public class SeleniumWrapper {

    WebDriver driver;

    public SeleniumWrapper(WebDriver driver){
        this.driver = driver;
    }

    public void clickElement(By locator){
        clickElement(locator, false);
    }

    public void clickElement(By locator, boolean takeScreenshot){
        driver.findElement(locator).click();
        logStep("Clicked on Element", takeScreenshot);
    }

    public void sendText(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

    public void gotoApplication(String url){
        gotoApplication(url, true);

    }

    public void gotoApplication(String url, boolean takeScreenshot){
        driver.get(url);
        logStep("Going to url", takeScreenshot);

    }

    public String getText(By locator){
        return driver.findElement(locator).getText();

    }

    private void logStep(String massage, boolean takeScreenShot){

        if(takeScreenShot){
            try {
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                ExtentManager.getTest().info(massage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }catch (Exception e) {
                e.printStackTrace();
                ExtentManager.getTest().info(massage + "Could not capture screenShot");
            }

        }
    }
}

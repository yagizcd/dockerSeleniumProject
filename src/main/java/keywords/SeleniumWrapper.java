package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumWrapper {

    WebDriver driver;

    public SeleniumWrapper(WebDriver driver){
        this.driver = driver;
    }

    public void clickElement(By locator){
        driver.findElement(locator).click();
    }

    public void sendText(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

    public void gotoApplication(String url){
        driver.get(url);
    }

    public String getText(By locator){
        return driver.findElement(locator).getText();

    }
}

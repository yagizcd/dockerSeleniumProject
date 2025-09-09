package pages;

import keywords.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage{

    SeleniumWrapper seleniumWrapper;

    private final By getTitleOfProductPage = By.xpath("//div[contains(text(),'Products')]");
    private final By getTitleOfFirstProduct = By.xpath("//a[@id='item_4_title_link']//div");

    public ProductPage(WebDriver driver){
        this.seleniumWrapper = new SeleniumWrapper(driver);
    }

    public String getTitleOfProducts(){
        return seleniumWrapper.getText(getTitleOfProductPage);
    }

    public String getFirstItem(){
        return seleniumWrapper.getText(getTitleOfFirstProduct);

    }
}

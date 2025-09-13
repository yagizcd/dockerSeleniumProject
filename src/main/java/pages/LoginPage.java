package pages;

import keywords.DriverFactory;
import keywords.SeleniumWrapper;
import org.openqa.selenium.By;

public class LoginPage {

    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");

    private final By loginButton = By.id("login-button");

    SeleniumWrapper seleniumWrapper;

    public LoginPage(){
        this.seleniumWrapper = new SeleniumWrapper(DriverFactory.getDriver());
    }


    public ProductPage userLogin(String username, String password){
        seleniumWrapper.gotoApplication("https://www.saucedemo.com/v1/");
        seleniumWrapper.sendText(usernameTextField, username);
        seleniumWrapper.sendText(passwordTextField, password);
        seleniumWrapper.clickElement(loginButton);
        return new ProductPage(DriverFactory.getDriver());
    }
}

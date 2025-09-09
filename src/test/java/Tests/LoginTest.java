package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTest extends BaseTest{

    LoginPage loginPage;

    ProductPage productPage;

    @Test
    public void UserCanBeAbleToLogin(){
        loginPage = new LoginPage();
        productPage = loginPage.userLogin("standard_user", "secret_sauce");
        String title = productPage.getTitleOfProducts();
        Assert.assertEquals(title, "Products");
    }
}

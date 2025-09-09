package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;

    @Test
    public void getFirstProductTest(){
        loginPage = new LoginPage();
        productPage = loginPage.userLogin("standard_user","secret_sauce");

        Assert.assertEquals(productPage.getFirstItem(),"Sauce Labs Backpack");
    }
}

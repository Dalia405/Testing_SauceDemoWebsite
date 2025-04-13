package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.YourCartPage;
import tests.base.BaseTest;

public class YourCartTest extends BaseTest {
    YourCartPage YourCartP;
//    WebDriverWait wait;

    @Test(priority = 1)
    public void testGoToYourCart(){
        BaseTest.getDriver().findElement(By.className("shopping_cart_link")).click();
//        wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
    }

//    @Test(priority = 2)
//    public void testClickOnCheckout(){
//        YourCartP=new YourCartPage(BaseTest.getDriver());
//        YourCartP.clickOnCheckout();
//        BaseTest.getDriver().navigate().back();
//    }
    @Test(priority = 2)
    public void testClickOnContinueShopping(){
        YourCartP=new YourCartPage(BaseTest.getDriver());
        YourCartP.clickOnContinueShopping();
        BaseTest.getDriver().navigate().back(); // moves forward in browser history
    }
    @Test(priority = 3)
    public void testTitleOfAllProductInCart(){
        YourCartP=new YourCartPage(BaseTest.getDriver());
        YourCartP.checkTitleOfAllProductInCart();
    }


    //@Test(priority = 2)
//    public void testGetAllCartTitles(){
//        YourCartP=new YourCartPage(BaseTest.getDriver());
//        YourCartP.getAllCartTitles();//don't working
//    }
    //@Test(priority = 3,dependsOnMethods = "testGetAllCartTitles")
//    @Test(priority = 5)
//    public void testRemoveOneCart(){
//        YourCartP=new YourCartPage(BaseTest.getDriver());
//        YourCartP.removeOneCart();
//    }
    @Test(priority = 4)
    public void testRemoveCart(){
        YourCartP=new YourCartPage(BaseTest.getDriver());
        YourCartP.removeAllCart();
    }
    @Test(priority = 5)
    public void testClickOnCheckout(){
        YourCartP=new YourCartPage(BaseTest.getDriver());
        YourCartP.clickOnCheckout();
    }
}

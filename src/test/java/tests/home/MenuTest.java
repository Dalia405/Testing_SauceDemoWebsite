package tests.home;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.home.HomePage;
import tests.base.BaseTest;

import java.util.List;


public class MenuTest extends BaseTest {
    HomePage HomeP;
    //menu
    @Test(priority=1)
    public void testOpenMenu(){
        HomeP=new HomePage(BaseTest.getDriver());
        HomeP.openMenu();
    }
    @DataProvider(name = "sidebar")
    public Object[][] sideBarPath(){
        return new Object[][]{
                 //https://x.com/saucelabs
                {"inventory_sidebar_link","https://www.saucedemo.com/inventory.html"},
                {"about_sidebar_link","https://saucelabs.com/"},
                {"reset_sidebar_link","https://www.saucedemo.com/inventory.html"},
                {"logout_sidebar_link","https://www.saucedemo.com/"}
        };
    }
    @Test(priority=2,dataProvider ="sidebar" )
    public void testSideBarLinks(String idElement,String expectedUrl){
        HomeP=new HomePage(BaseTest.getDriver());
        HomeP.sideBarLinks(idElement,expectedUrl);
    }

    @DataProvider(name = "resetLink")
    public Object[][] reset(){
        return new Object[][]{
                //https://x.com/saucelabs

                {"about_sidebar_link","https://saucelabs.com/"},
                {"reset_sidebar_link","https://www.saucedemo.com/inventory.html"},
                {"inventory_sidebar_link","https://www.saucedemo.com/inventory.html"}
        };
    }
    @Test(priority=2,dataProvider ="resetLink" )
    public void testMenuExistAllPages(String idElement,String expectedUrl){
        HomeP=new HomePage(BaseTest.getDriver());
        HomeP.sideBarLinks(idElement,expectedUrl);
        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory"));
        Assert.assertTrue(removeButtons.isEmpty(),"'Remove' buttons should not be visible after removing all items.");
//        BaseTest.getDriver().navigate().refresh();

    }
    @Test(priority=3)
    public void testCloseMenu(){
        HomeP=new HomePage(BaseTest.getDriver());
        HomeP.closeMenu();
    }
}

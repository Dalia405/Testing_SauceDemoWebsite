package tests.home;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.home.HomePage;
import tests.base.BaseTest;


public class MenuTest extends BaseTest {
    HomePage HomeP;
    //menu
    @Test(priority=1)
    public void testOpenMenu(){
        HomeP=new HomePage(BaseTest.getDriver());
        HomeP.openMenu();
    }
   // @Test(priority=2)
    public void testCloseMenu(){
       HomeP=new HomePage(BaseTest.getDriver());
       HomeP.closeMenu();
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
}

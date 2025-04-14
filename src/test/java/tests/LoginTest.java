package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.base.BaseTest;

public class LoginTest extends BaseTest {
    LoginPage LoginP;
    //Valid
    @DataProvider(name = "validData")
    public Object[][] validTestData(){
        return new Object[][]{
                {"standard_user","secret_sauce","https://www.saucedemo.com/inventory.html"},
//                {"locked_out_user","secret_sauce","\uD83D\uDE1E:Sorry, this user has been locked out."},
//                {"problem_user","secret_sauce","https://www.saucedemo.com/inventory.html"},
//                {"performance_glitch_user","secret_sauce","https://www.saucedemo.com/inventory.html"},
//                {"error_user","secret_sauce","https://www.saucedemo.com/inventory.html"},
//                {"visual_user","secret_sauce","https://www.saucedemo.com/inventory.html"},
        };
    }
    @Test(priority=2,dataProvider = "validData")
    public void testValidLogin(String userName,String password,String expectedResult){
        //WebDriver driver = BaseTest.getDriver(); // Now always initialized
        // Use driver safely
        System.out.println(BaseTest.getDriver());
        LoginP =new LoginPage(BaseTest.getDriver());
        LoginP.login(userName,password,expectedResult);
        //driver.navigate().back();
    }

    //InValid
    @DataProvider(name = "invalidData")
    public Object[][] invalidTestData(){
        return new Object[][]{
                {"", "","\uD83D\uDE1E: Username is required"},                         // both empty
                {"invalid_user", "","\uD83D\uDE1E: Password is required"}, // invalid username
                {"standard_user", "wrong_pass","\uD83D\uDE1E: Username and password do not match any user in this service"},  // invalid password
                {"test", "1234","\uD83D\uDE1E: Username and password do not match any user in this service"}                  // completely invalid
        };
    }
    @Test(priority=1,dataProvider = "invalidData")
    public void testInValidLogin(String userName,String password,String expectedResult){
        LoginP =new LoginPage(BaseTest.getDriver());
        LoginP.login(userName,password,expectedResult);
        //driver.navigate().back();
    }

    @Test
    public void testGotTOHomePage(){
        BaseTest.getDriver().get("https://www.saucedemo.com/inventory.html");
    }
}

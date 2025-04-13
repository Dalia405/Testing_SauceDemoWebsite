package tests.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.checkout.CheckOutInfoPage;
import tests.base.BaseTest;
import java.time.Duration;

public class CheckOutInfoTest extends BaseTest {
    CheckOutInfoPage CheckOutInfoP;
    WebDriverWait wait;
    /*RUKA*/
    @Test(priority = 1)
    public void testGoToCheckOut(){
//            BaseTest.getDriver().findElement(By.className("shopping_cart_link")).click();
            wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
        }
    /*RUKA*/

    //valid
    @DataProvider(name = "validData")
    public Object[][] validTestFormCheckout() {
        return new Object[][]{
                {"test first", "test last", "12345", "https://www.saucedemo.com/checkout-step-two.html"},
        };
    }
    @Test(priority = 4, dataProvider = "validData")
    public void testValidFormSubmission(String firstName, String lastName, String postalCode, String expectedURL) {
       CheckOutInfoP=new CheckOutInfoPage(BaseTest.getDriver());
       CheckOutInfoP.validFormSubmission(firstName,lastName,postalCode,expectedURL);
    }
    //invalid
    @DataProvider(name = "invalidData")
    public Object[][] invalidTestFormCheckout() {
        return new Object[][]{
                {"", "Last", "12345", "Error: First Name is required"},
                {"First", "", "12345", "Error: Last Name is required"},
                {"First", "Last", "", "Error: Postal Code is required"},
                {"", "", "", "Error: First Name is required"}, // First Name is required shown first
        };
    }
    @Test(priority = 3, dataProvider = "invalidData")
    public void testInValidFormSubmission(String firstName, String lastName, String postalCode, String expectedError) {
        CheckOutInfoP=new CheckOutInfoPage(BaseTest.getDriver());
        CheckOutInfoP.inValidFormSubmission(firstName,lastName,postalCode,expectedError);
    }
    @Test(priority = 2)
    public void testGoBack(){
        CheckOutInfoP=new CheckOutInfoPage(BaseTest.getDriver());
        CheckOutInfoP.goBack();
        BaseTest.getDriver().navigate().back();
    }

}

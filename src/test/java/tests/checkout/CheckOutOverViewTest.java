package tests.checkout;

import org.testng.annotations.Test;
import pages.checkout.CheckOutOverViewPage;
import tests.base.BaseTest;

public class CheckOutOverViewTest extends BaseTest {
    CheckOutOverViewPage CheckOutOverViewP;

    @Test(priority = 2)
    public void testCartCalculations(){
        CheckOutOverViewP=new CheckOutOverViewPage(BaseTest.getDriver());
        CheckOutOverViewP.verifyCartCalculations();
    }
    @Test(priority = 4)
    public void testClickOnFinishBTN(){
        CheckOutOverViewP=new CheckOutOverViewPage(BaseTest.getDriver());
        CheckOutOverViewP.clickOnFinishBTN();

    }
    @Test(priority = 1)
    public void testClickOnCancelBTN(){
        CheckOutOverViewP=new CheckOutOverViewPage(BaseTest.getDriver());
        CheckOutOverViewP.clickOnCancelBTN();
        BaseTest.getDriver().navigate().back();
    }
    @Test(priority = 3)
    public void testTitleOfAllProductInCart(){
        CheckOutOverViewP=new CheckOutOverViewPage(BaseTest.getDriver());
        CheckOutOverViewP.checkTitleOfAllProductInCart();
    }
}

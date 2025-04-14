package tests.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import java.util.List;

import tests.base.BaseTest;

public class AddToCart extends BaseTest {
    int addedProductsCount = 0;
    @Test(priority = 0)
    public void addProductsToCart() {
        try {
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));
            for (int i = 0; i < Math.min(6, addToCartButtons.size()); i++) {
                System.out.println("Adding product to cart: " + addToCartButtons.get(i).getAttribute("name"));
                addToCartButtons.get(i).click();
                addedProductsCount++;
                Thread.sleep(1000);
            }
            verifyButtonsSwitchedToRemove(addedProductsCount);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 1)
    public void verifyButtonsSwitchedToRemove(int expectedCount) {
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));
        Assert.assertEquals(removeButtons.size(), expectedCount, "Mismatch in number of 'Remove' buttons.");

        for (int i = 0; i < removeButtons.size(); i++) {
            WebElement button = removeButtons.get(i);
            Assert.assertEquals(button.getText(), "Remove", "Button at index " + i + " did not switch to 'Remove'");
        }

        System.out.println("All " + expectedCount + " buttons switched to 'Remove' successfully.");
    }


    @Test(priority = 2)
    public void VerifyCartCount(){
        try {
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
            String badgeText = cartBadge.getText();
            int badgeCount = Integer.parseInt(badgeText);

            System.out.println("Expected cart count: " + addedProductsCount);
            System.out.println("Cart badge shows: " + badgeCount);

            Assert.assertEquals(badgeCount, addedProductsCount, "Cart count does not match number of added products!");
        }
        catch (Exception e) {
            System.out.println("Failed to verify cart badge.");
            e.printStackTrace();
            Assert.fail("Exception occurred while verifying cart count.");
        }
    }
}

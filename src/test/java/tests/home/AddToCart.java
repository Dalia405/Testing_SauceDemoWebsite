package tests.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import java.util.List;

import tests.base.BaseTest;

public class AddToCart extends BaseTest {

    @Test
    public void addProductsToCart() {
        try {
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));
            for (int i = 0; i < Math.min(6, addToCartButtons.size()); i++) {
                System.out.println("Adding product to cart: " + addToCartButtons.get(i).getAttribute("name"));
                addToCartButtons.get(i).click();
                Thread.sleep(1000);
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

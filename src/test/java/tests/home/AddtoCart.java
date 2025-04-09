package tests.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import java.util.List;

import tests.base.BaseTest;

public class AddtoCart extends BaseTest {

    @Test
    public void addProductsToCart() {
        try {
            WebDriver driver = getDriver(); 

            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000); 

            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));

            for (int i = 0; i < Math.min(6, addToCartButtons.size()); i++) {
                System.out.println("Adding product to cart: " + addToCartButtons.get(i).getAttribute("name"));
                addToCartButtons.get(i).click();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit(); 
        }
    }
}

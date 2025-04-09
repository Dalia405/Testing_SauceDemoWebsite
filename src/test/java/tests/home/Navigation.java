package tests.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.List;

import tests.base.BaseTest;

public class Navigation extends BaseTest {

    @BeforeTest
    @Override
    public void BeforeClass() {
        super.BeforeClass();
    }

    @Test
    public void navigateProducts() {
        try {
            WebDriver driver = getDriver(); 
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000); 

            List<WebElement> productLinks = driver.findElements(By.className("inventory_item_name"));

            for (int i = 0; i < Math.min(6, productLinks.size()); i++) {
                productLinks = driver.findElements(By.className("inventory_item_name")); 
                System.out.println("Visiting product: " + productLinks.get(i).getText());
                productLinks.get(i).click();
                Thread.sleep(1000);
                driver.navigate().back();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        
        // super.AfterClass();
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}

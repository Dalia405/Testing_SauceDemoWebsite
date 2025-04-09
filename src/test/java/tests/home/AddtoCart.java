import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import java.util.List;

public class AddtoCart {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new EdgeDriver();
    }

    @Test
    public void addProductsToCart() {
        try {
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000); // wait for login

            // Find all "Add to cart" buttons
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));

            // Click first 6 buttons (or fewer)
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
        if (driver != null) {
            driver.quit();
        }
    }
}

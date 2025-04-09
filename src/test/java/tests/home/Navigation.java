import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class Navigation {
    public static void main(String[] args) {



        WebDriver driver = new EdgeDriver();

        try {
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000); // wait for login

            // Get product elements
            List<WebElement> productLinks = driver.findElements(By.className("inventory_item_name"));

            // Visit first 3 products
            for (int i = 0; i < Math.min(6, productLinks.size()); i++) {
                productLinks = driver.findElements(By.className("inventory_item_name")); // re-fetch elements
                System.out.println("Visiting product: " + productLinks.get(i).getText());
                productLinks.get(i).click();
                Thread.sleep(1000);
                driver.navigate().back();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

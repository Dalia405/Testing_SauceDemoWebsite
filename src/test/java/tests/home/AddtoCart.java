import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class AddtoCart {
    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        try {
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
        } finally {
            driver.quit();
        }
    }
}

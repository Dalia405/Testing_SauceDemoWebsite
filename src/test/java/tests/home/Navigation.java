package tests.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import tests.base.BaseTest;

public class Navigation extends BaseTest {

    @Test
    public void navigateProducts() {
        try {
            List<WebElement> productLinks = driver.findElements(By.className("inventory_item_name"));

            for (int i = 0; i < Math.min(6, productLinks.size()); i++) {
                // productLinks = driver.findElements(By.className("inventory_item_name"));
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
}

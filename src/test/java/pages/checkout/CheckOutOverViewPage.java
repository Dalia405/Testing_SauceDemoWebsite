package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.List;

public class CheckOutOverViewPage {
    WebDriver driver;
    WebDriverWait wait;
// Locators
    private final By _subTotal = By.className("summary_subtotal_label");
    private final By _tax = By.className("summary_tax_label");
    private final By _totalPrice = By.className("summary_total_label");
    private final By _finishButton = By.id("finish");
    private final By _cancelButton = By.id("cancel");
    By _badgeClass = By.className("shopping_cart_badge");
    // Constructor
    public CheckOutOverViewPage(WebDriver driver) {this.driver=driver;}

    public void verifyCartCalculations() {
        List<WebElement> products = driver.findElements(By.className("cart_item_label"));
        List<WebElement> badges = driver.findElements(_badgeClass);

        // Assert cart badge equals number of cart items
        if(badges.size()!=0){
            int badgeCount = Integer.parseInt(badges.get(0).getText());
            Assert.assertEquals(badgeCount, products.size(), "Badge count and product count mismatch");
        }

        float calculatedTotal = 0;

        for (WebElement product : products) {
//            String name = product.findElement(By.className("inventory_item_name")).getText();
//            String desc = product.findElement(By.className("inventory_item_desc")).getText();
            String priceText = product.findElement(By.className("inventory_item_price")).getText().replace("$", "");

            float price = Float.parseFloat(priceText);
            calculatedTotal += price;
            System.out.println("total "+calculatedTotal);
        }

        // Get prices from page
        float displayedSubtotal = Float.parseFloat(driver.findElement(_subTotal).getText().replaceAll("[^\\d.]", ""));
        float displayedTax = Float.parseFloat(driver.findElement(_tax).getText().replaceAll("[^\\d.]", ""));
        float displayedTotal = Float.parseFloat(driver.findElement(_totalPrice).getText().replaceAll("[^\\d.]", ""));

        // Assertions
        Assert.assertEquals(calculatedTotal, displayedSubtotal, "Subtotal mismatch");
        Assert.assertEquals(displayedSubtotal + displayedTax, displayedTotal, "Total after tax mismatch");
    }
    public void clickOnFinishBTN() {
        wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        WebElement _finishBTN= wait.until(ExpectedConditions.elementToBeClickable(_finishButton));
        _finishBTN.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html","URL Mismatched");
        List<WebElement> badges=driver.findElements(_badgeClass);
        Assert.assertTrue(badges.isEmpty(),"Expected empty cart badge, but found " + badges.size());
        driver.navigate().back();

        Assert.assertEquals(driver.findElement(_subTotal).getText(),"Item total: $0","Item subtotal does not match expected value");
        Assert.assertEquals(driver.findElement(_tax).getText(),"Tax: $0.00","tax does not match expected value");
        Assert.assertEquals(driver.findElement(_totalPrice).getText(),"Total: $0.00","Item total does not match expected value");
    }
    public void clickOnCancelBTN() {
        wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        WebElement _finishBTN= wait.until(ExpectedConditions.elementToBeClickable(_cancelButton));
        _finishBTN.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","URL Mismatched");
    }
    //extract ID number from link ID
    private String extractIdNumber(WebElement linkElement) {
        String idAttr = linkElement.getAttribute("id");//item_4_title_link
        if (idAttr != null) {
            return idAttr.replaceAll("\\D+", "");// removes non-digits //4
        }
        return "0";
    }
    public void checkTitleOfAllProductInCart(){
        int productCount = driver.findElements(By.className("cart_item_label")).size();
        for (int i = 0; i < productCount; i++) {
            List<WebElement> products=driver.findElements(By.className("cart_item_label"));
            WebElement product = products.get(i);
            //for(WebElement product:products){
            WebElement link = product.findElement(By.tagName("a"));
            String number= extractIdNumber(link);
            System.out.println(number);

            String itemName= product.findElement(By.className("inventory_item_name")).getText();
            String itemDesc=product.findElement(By.className("inventory_item_desc")).getText();
            String itemPrice=product.findElement(By.className("inventory_item_price")).getText();

            link.click();

            Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory-item.html?id="+number,"URL Mismatched");

            WebElement itemPage = driver.findElement(By.cssSelector("[data-test='inventory-item']"));
//            String productImage=itemPage.findElement(By.className("inventory_details_img")).getAttribute("src");
            String productName= itemPage.findElement(By.className("inventory_details_name")).getText();
            String productDesc=itemPage.findElement(By.className("inventory_details_desc")).getText();
            String productPrice=itemPage.findElement(By.className("inventory_details_price")).getText();

            Assert.assertEquals(productName, itemName, "Product name mismatch");
            Assert.assertEquals(productDesc, itemDesc, "Product description mismatch");
            Assert.assertEquals(productPrice, itemPrice, "Product price mismatch");

            driver.navigate().back();
        }
    }
}

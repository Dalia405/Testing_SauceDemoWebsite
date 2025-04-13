package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class YourCartPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
//    private final By _cartItemName = By.className("inventory_item_name");
     By _removeClass = By.className("cart_button");
     By _badgeClass = By.className("shopping_cart_badge");
     By _checkoutButton = By.id("checkout");
     By _continueShoppingButton = By.id("continue-shopping");
//     By _cartItems = By.className("cart_item");
    List<String> titles = new ArrayList<>();

    // Constructor
    public YourCartPage(WebDriver driver) {
        this.driver = driver;
    }
    //actions
    public void clickOnCheckout() {
       wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
       WebElement _checkoutBTN= wait.until(ExpectedConditions.elementToBeClickable(_checkoutButton));
        _checkoutBTN.click();
//        WebElement element = driver.findElement(_checkoutButton);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        element.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html","URL Mismatched");
    }
    public void clickOnContinueShopping() {
        wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        WebElement _continueBTN= wait.until(ExpectedConditions.elementToBeClickable(_continueShoppingButton));
        _continueBTN.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","URL Mismatched");
    }
    // Get all product titles in the cart
    public void getAllCartTitles() {
        List<WebElement> products = driver.findElements(By.className("cart_item_label"));
        titles.clear();

        for (WebElement product : products) {
            String title = product.findElement(By.className("inventory_item_name")).getText();
            titles.add(title);

            String number = extractIdNumber(product.findElement(By.tagName("a")));
            System.out.println(number);
        }
        System.out.println("number of broduct"+titles.size()+titles.get(0));
    }
    // Get all product titles in the cart
    public List<WebElement> getAllCart() {
        List<WebElement> products = driver.findElements(By.className("cart_item_label"));
        return products;
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
    public void removeAllCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        List<WebElement> removeBTNS= driver.findElements(_removeClass);
        List<WebElement> badges=driver.findElements(_badgeClass);

        if (!badges.isEmpty()) {
            int initialCount = Integer.parseInt(badges.get(0).getText());

            for (WebElement removeBTN : removeBTNS) {
                removeBTN.click();
                initialCount--;
                // Wait : allow DOM to update after click
//                try {
//                    Thread.sleep(300); // or better: use WebDriverWait
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                int finalInitialCount = initialCount;
                wait.until(driver -> {
                    List<WebElement> updated = driver.findElements(By.className("shopping_cart_badge"));
                    return updated.isEmpty() || Integer.parseInt(updated.get(0).getText()) == finalInitialCount;
                });
                List<WebElement> updatedCartBadge = driver.findElements(By.className("shopping_cart_badge"));

                if (!updatedCartBadge.isEmpty()) {
                    int finalCount = Integer.parseInt(updatedCartBadge.get(0).getText());
                    Assert.assertEquals(finalCount, initialCount, "Cart count did not decrease by 1");
                    System.out.println("Cart count is now: " + finalCount);
                } else {
                    // When cart becomes empty and badge disappears
                    Assert.assertEquals(initialCount, 0, "Cart should be empty now");
                    System.out.println("Cart is now empty. No badge displayed.");
                }
            }
        } else {
            System.out.println("Cart is already empty. No badge to remove.");
        }
    }
    //extract ID number from link ID
    private String extractIdNumber(WebElement linkElement) {
        String idAttr = linkElement.getAttribute("id");//item_4_title_link
        if (idAttr != null) {
            return idAttr.replaceAll("\\D+", "");// removes non-digits //4
        }
        return "0";
    }
    // Remove one product (by title)
    public void removeOneCart() {
        getAllCartTitles();
        if (!titles.isEmpty()) {
            //id="remove-sauce-labs-backpack" of remove btn
            String title = titles.get(0).toLowerCase().replace(" ", "-");//Sauce Labs Backpack to sauce-labs-backpack
            System.out.println("remove-" +title);
            WebElement removeButton = driver.findElement(By.id("remove-" +title));//id="remove-sauce-labs-backpack"
            removeButton.click();
        }
        else{
            System.out.println("empty ");
        }
    }
}

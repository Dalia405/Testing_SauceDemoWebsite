package tests.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FiltrationTest extends BaseTest {

    @DataProvider(name = "filterName")
    public Object[][] validTestFilterName(){
        return new Object[][]{
                {"az","Products are not sorted by name A to Z."},
                {"za","Products are not sorted by name Z to A."},
        };
    }
    @Test(dataProvider = "filterName")
    public void testNameSorting(String value,String errorMSG) throws InterruptedException {
        // Select "Name (A to Z)" or "Name (Z to A)" from dropdown
        Select sortDropdown = new Select(BaseTest.getDriver().findElement(By.cssSelector("select.product_sort_container")));
        sortDropdown.selectByValue(value); // Or .selectByVisibleText("Name (A to Z)");
        Thread.sleep(2000); // Use WebDriverWait in real code
        // Get all product names
        List<WebElement> nameElements = BaseTest.getDriver().findElements(By.className("inventory_item_name"));
        List<String> actualNames = new ArrayList<>();
        for (WebElement name : nameElements) {
            actualNames.add(name.getText());
        }
        // Copy and sort to compare
        List<String> expectedNames = new ArrayList<>(actualNames);

        if(value.contains("az")){
            Collections.sort(expectedNames);
        }
        if(value.contains("za")){
            expectedNames.sort(Collections.reverseOrder());
        }
        //how assert actual and expected //how get expected
        Assert.assertEquals(actualNames, expectedNames, errorMSG);
    }

    @DataProvider(name = "filterPrice")
    public Object[][] validTestFilterPrice(){
        return new Object[][]{
                {"lohi","Prices are not sorted from low to high."},
                {"hilo","Prices are not sorted from high to low."},
        };
    }
    @Test(dataProvider = "filterPrice")
    public void testPriceSorting(String value,String errorMSG) throws InterruptedException {
        // Select "Name (A to Z)" or "Name (Z to A)" from dropdown
        Select sortDropdown = new Select(BaseTest.getDriver().findElement(By.cssSelector("select.product_sort_container")));
        sortDropdown.selectByValue(value); // Or .selectByVisibleText("Name (A to Z)");
        Thread.sleep(2000); // Use WebDriverWait in real code
        // Get all price elements
        List<WebElement> priceElements = BaseTest.getDriver().findElements(By.className("inventory_item_price"));
        List<Double> actualPrices = new ArrayList<>();

        for (WebElement price : priceElements) {
            String priceText = price.getText().replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Create a sorted list in descending order
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());


        // Copy and sort to compare
        List<Double> expectedNames = new ArrayList<>(actualPrices);
        if(value.contains("lohi")){
            Collections.sort(expectedPrices);
        }
        if(value.contains("hilo")){
            expectedPrices.sort(Collections.reverseOrder());
        }
        // Compare actual and expected
        Assert.assertEquals(actualPrices, expectedNames, errorMSG);
    }

//    @Test
//    public void testNameAToZSorting() throws InterruptedException {
//        // Select "Name (A to Z)" from dropdown
//        Select sortDropdown = new Select(BaseTest.getDriver().findElement(By.cssSelector("select.product_sort_container")));
//        sortDropdown.selectByValue("az"); // Or .selectByVisibleText("Name (A to Z)");
//        Thread.sleep(2000); // Use WebDriverWait in real code
//
//        // Get all product names
//        List<WebElement> nameElements = BaseTest.getDriver().findElements(By.className("inventory_item_name"));
//        List<String> actualNames = new ArrayList<>();
//
//        for (WebElement name : nameElements) {
//            actualNames.add(name.getText());
//        }
//
//        // Copy and sort to compare
//        List<String> expectedNames = new ArrayList<>(actualNames);
//        Collections.sort(expectedNames);
//
//        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted by name A to Z.");
//    }
//    @Test
//    public void testNameZToASorting() throws InterruptedException {
//        // Select "Name (Z to A)" from the dropdown
//        Select sortDropdown = new Select(BaseTest.getDriver().findElement(By.cssSelector("select.product_sort_container")));
//        sortDropdown.selectByValue("za"); // or .selectByVisibleText("Name (Z to A)");
//
//        Thread.sleep(2000); // Use WebDriverWait in practice
//
//        // Get all product names
//        List<WebElement> nameElements = BaseTest.getDriver().findElements(By.className("inventory_item_name"));
//        List<String> actualNames = new ArrayList<>();
//
//        for (WebElement name : nameElements) {
//            actualNames.add(name.getText());
//        }
//
//        // Make a sorted copy and reverse it
//        List<String> expectedNames = new ArrayList<>(actualNames);
//        expectedNames.sort(Collections.reverseOrder());
//
//        // Assert both lists match
//        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted by name Z to A.");
//    }
    //    @Test
//    public void testPriceLowToHighSorting() throws InterruptedException {
//        // Select "Price (low to high)" from the dropdown
//        Select sortDropdown = new Select(BaseTest.getDriver().findElement(By.cssSelector("select.product_sort_container")));
//        sortDropdown.selectByValue("lohi");
//
//        Thread.sleep(2000); // Use WebDriverWait in real tests
//
//        // Get all displayed prices
//        List<WebElement> priceElements = BaseTest.getDriver().findElements(By.className("inventory_item_price"));
//        List<Double> actualPrices = new ArrayList<>();
//
//        for (WebElement priceElement : priceElements) {
//            String priceText = priceElement.getText().replace("$", "").trim();
//            actualPrices.add(Double.parseDouble(priceText));
//        }
//
//        // Sort a copy of the list
//        List<Double> expectedPrices = new ArrayList<>(actualPrices);
//        Collections.sort(expectedPrices);
//
//        // Assert actual list equals sorted list
//        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted from low to high.");
//    }
//    @Test
//    public void testPriceHighToLowSorting() throws InterruptedException {
//        // Select "Price (high to low)" from the dropdown
//        Select sortDropdown = new Select(BaseTest.getDriver().findElement(By.cssSelector("select.product_sort_container")));
//        sortDropdown.selectByValue("hilo");
//
//        Thread.sleep(2000); // Use WebDriverWait for dynamic loading
//
//        // Get all price elements
//        List<WebElement> priceElements = BaseTest.getDriver().findElements(By.className("inventory_item_price"));
//        List<Double> actualPrices = new ArrayList<>();
//
//        for (WebElement price : priceElements) {
//            String priceText = price.getText().replace("$", "").trim();
//            actualPrices.add(Double.parseDouble(priceText));
//        }
//
//        // Create a sorted list in descending order
//        List<Double> expectedPrices = new ArrayList<>(actualPrices);
//        expectedPrices.sort(Collections.reverseOrder());
//
//        // Compare actual and expected
//        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted from high to low.");
//    }


}



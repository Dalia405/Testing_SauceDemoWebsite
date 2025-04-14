package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.List;

public class CheckOutInfoPage {
    WebDriver driver;
    //locator
    By _firstName = By.id("first-name");
    By _lastName = By.id("last-name");
    By _postalCode = By.id("postal-code");
    By _continueBTN = By.id("continue");
    By _errorMSG=By.cssSelector("#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error > h3");
    By _closeErrorBTN=By.className("error-button");

    public CheckOutInfoPage(WebDriver driver){
        this.driver=driver;
    }
    //actions
    public void clearAndType(By locator, String value) {
        WebElement element = BaseTest.getDriver().findElement(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }
    //valid
    public void validFormSubmission(String firstName, String lastName, String postalCode, String expectedURL){
        // Fill out form
        clearAndType(_firstName,firstName);
        clearAndType(_lastName,lastName);
        clearAndType(_postalCode,postalCode);

        driver.findElement(_continueBTN).click();
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "URL mismatch");
    }
    //invalid
    public void inValidFormSubmission(String firstName, String lastName, String postalCode, String expectedError){


        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        // Fill out form
        clearAndType(_firstName,firstName);
        //when i run {"First", "", "12345", "Error: Last Name is required"},
        //I'm expecting the Last Name field to be empty during the test case:
        //but the Last Name input still contains a value (like Last) when the test runs
        // That usually happens because the input is not being cleared properly.
        // HOW .clear is not working
        //driver.findElement(_lastName).clear();
        //driver.findElement(_lastName).sendKeys(lastName);
        clearAndType(_lastName,lastName);
        clearAndType(_postalCode,postalCode);

        driver.findElement(_continueBTN).click();

        if(!firstName.isEmpty()&&!lastName.isEmpty()&&!postalCode.isEmpty()){

            boolean isValidFirstName = firstName.matches("^[a-zA-Z]+$");
            boolean isValidLastName = lastName.matches("^[a-zA-Z]+$");
            // Check if it's numbers only
            boolean isOnlyNumbers = postalCode.matches("\\d+");

            if(!isValidFirstName||isValidLastName){
                Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", expectedError);
            }
            if(!isOnlyNumbers){
                Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", expectedError);

            }
        }

        // Wait for error message
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(_errorMSG));
        Assert.assertEquals(errorElement.getText(), expectedError, "Error message mismatch");

        WebElement closeErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(_closeErrorBTN));
        //where i add it closeErrorElement.click();

        // Get all error icons
        List<WebElement> errorIcons = driver.findElements(By.cssSelector("svg.svg-inline--fa.fa-times-circle.fa-w-16.error_icon"));
        // Validate visibility of icons for each field
        if (firstName.isEmpty()) {
            Assert.assertTrue(errorIcons.getFirst().isDisplayed(), "First Name error icon should be visible");
        } else {
            Assert.assertFalse(errorIcons.getFirst().isDisplayed(), "First Name error icon should NOT be visible");
        }

        if (lastName.isEmpty()) {
            Assert.assertTrue(errorIcons.get(1).isDisplayed(), "Last Name error icon should be visible");
        } else {
            Assert.assertFalse(errorIcons.get(1).isDisplayed(), "Last Name error icon should NOT be visible");
        }

        if (postalCode.isEmpty()) {
            Assert.assertTrue(errorIcons.get(2).isDisplayed(), "Postal Code error icon should be visible");
        } else {
            Assert.assertFalse(errorIcons.get(2).isDisplayed(), "Postal Code error icon should NOT be visible");
        }
    }

    public void goBack(){
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        //locator
        By _cancel = By.id("cancel");

        WebElement errorElement = wait.until(ExpectedConditions.elementToBeClickable(_cancel));
        errorElement.click();
        String expectedURL="https://www.saucedemo.com/cart.html";
        Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), expectedURL, "Error message mismatch");
    }
}

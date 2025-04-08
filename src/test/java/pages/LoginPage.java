package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    // Locators
    By _userName=By.id("user-name");
    By _password=By.id("password");
    By _loginBTN=By.id("login-button");
    By errorMessage = By.cssSelector(".error-message-container.error");
    //actions
    public void login(String userName, String password,String expectedResult) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(_userName).clear();
        driver.findElement(_userName).sendKeys(userName);
        driver.findElement(_password).clear();
        driver.findElement(_password).sendKeys(password);
        driver.findElement(_loginBTN).click();

        if(expectedResult.contains("https")){
            // Wait until the URL is the expected URL
            wait.until(ExpectedConditions.urlToBe(expectedResult));
            Assert.assertEquals(driver.getCurrentUrl(), expectedResult, "Login failed");
        }
        else {
            // Wait for the error message
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            // Assert the error message
            //String expectedError = "\uD83D\uDE1E  Sorry, this user has been locked out.";
            Assert.assertEquals(errorMsg.getText(), expectedResult, "Error message does not match for locked out user");
        }
/*
        // Wait until username field is visible and interact with it
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(_userName));
        usernameField.clear();
        usernameField.sendKeys(userName);
        // Wait until password field is visible and interact with it
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(_password));
        passwordField.clear();
        passwordField.sendKeys(password);
        // Wait until login button is clickable and click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(_loginBTN));
        loginButton.click();
*/

    }
}

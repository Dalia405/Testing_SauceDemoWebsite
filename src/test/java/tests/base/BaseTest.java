package tests.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;

import static javax.swing.UIManager.put;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite
    // @BeforeClass
    public void BeforeClass(){
        try {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
//            options.addArguments("--disable-popup-blocking");  // Disable browser's built-in popup blocking
//            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
        }
        catch (Exception e) {
            System.err.println("Error in @BeforeSuite: " + e.getMessage());
            e.printStackTrace();  // Print full error stack trace
        }

    }
    //  Method to get driver in test classes
    public static WebDriver getDriver() {
        return driver;
    }
    @AfterSuite
    //  @AfterClass
    public void AfterClass(){
        //driver.quit();
    }
}

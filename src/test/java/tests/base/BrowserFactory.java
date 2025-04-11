package tests.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver ;
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\ITI\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
              ChromeOptions options = new ChromeOptions();
              options.addArguments("--incognito");
//              options.addArguments("--disable-popup-blocking");  // Disable browser's built-in popup blocking
//              options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox" -> {
               // System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private"); // Open Firefox in Incognito
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        driver.manage().window().maximize();
        return driver;
    }

}

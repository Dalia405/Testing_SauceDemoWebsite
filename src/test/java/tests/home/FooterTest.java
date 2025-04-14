package tests.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.ArrayList;

public class FooterTest  extends BaseTest {
    @DataProvider(name = "pathSocial")
    public Object[][] socialIcons(){
        return new Object[][]{
                // https://x.com/saucelabs
                {"//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a","https://twitter.com/saucelabs"},
                {"//*[@id=\"page_wrapper\"]/footer/ul/li[2]/a","https://www.facebook.com/saucelabs"},
                {"//*[@id=\"page_wrapper\"]/footer/ul/li[3]/a","https://www.linkedin.com/company/sauce-labs/"},
        };
    }
    @Test(priority=1,dataProvider ="pathSocial" )
    public void testFooter(String pathSocial,String expectedUrl){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(pathSocial)));
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathSocial)));
        closeButton.click();
        Assert.assertEquals(BaseTest.getDriver().findElement(By.xpath(pathSocial)).getAttribute("href"),expectedUrl,"url mismatch");
        ArrayList<String> tabs = new ArrayList<>(BaseTest.getDriver().getWindowHandles());
//        System.out.println("tabs.size() "+ tabs.size());
        BaseTest.getDriver().switchTo().window(tabs.get(0));
    }
}

package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.List;

//menu -footer -filtration -cart
public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
    }
    /*Locators*/
    //menu
    By _menu=By.id("react-burger-menu-btn");
    By _menuWrap=By.className("bm-menu-wrap");


    /* Actions */
    //menu
    public void openMenu(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        By _menu=By.id("react-burger-menu-btn");
//        By _menuWrap=By.className("bm-menu-wrap");
       WebElement menu= wait.until(ExpectedConditions.elementToBeClickable(_menu));
       if(menu.isDisplayed()){
           menu.click();
           if (BaseTest.getDriver().findElement(_menuWrap).isDisplayed()){
               WebElement sideBar= wait.until(ExpectedConditions.visibilityOfElementLocated(_menuWrap));
               Assert.assertTrue(sideBar.isDisplayed(),"Menu did not collapse or wrap correctly after clicking on Menu.");
           }
       }
       else{
            Assert.assertTrue(menu.isDisplayed(),"Menu is not exist");
        }
    }
    public void closeMenu(){
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        WebElement close= wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-cross-btn")));
        //error:❌ Error Message: element click intercepted:
        // Element <button type="button" id="react-burger-cross-btn" style="position: absolute; left: 0px; top: 0px; z-index: 1; width: 100%; height: 100%; margin: 0px; padding: 0px; border: none; font-size: 0px; background: transparent; cursor: pointer;">...</button> is not clickable at point (192, 26).
        //Other element would receive the click: <div class="bm-menu" style="height: 100%; box-sizing: border-box; overflow: auto;">...</div>
        // WebElement close= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-burger-cross-btn\"]")));
        //WebElement close= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#react-burger-cross-btn")));
        close.click();
        By _menuWrap=By.className("bm-menu-wrap");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(_menuWrap));  // Adjust selector
        boolean isElementPresent = BaseTest.getDriver().findElement(_menuWrap).isDisplayed();
        Assert.assertFalse(isElementPresent,"❌ The close menu button is visible, but it shouldn't be.");
    }
    public void sideBarLinks(String idElement,String expectedUrl){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sideBarLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idElement)));

        if(sideBarLink.isDisplayed()){
            sideBarLink.click();

            //        5. If it opens a new tab (like the About link), switch to it and verify the URL
//            for (String handle : driver.getWindowHandles()) {
//                driver.switchTo().window(handle);
//            }
            if(idElement.contains("about_sidebar_link")){
               Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), expectedUrl, "URL mismatch");
               driver.navigate().back();
               openMenu();
               return;
            }
            if(idElement.contains("reset_sidebar_link")){
                List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));
                List<WebElement> removeButtons = driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory"));
                Assert.assertTrue(cartBadges.isEmpty(),"Cart badge is not empty! Found " + cartBadges.size() + " badge(s).");
                Assert.assertTrue(removeButtons.isEmpty(),"'Remove' buttons should not be visible after removing all items.");
            }
             wait.until(ExpectedConditions.urlToBe(expectedUrl));
            Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), expectedUrl, "URL mismatch");
        }
    }


}

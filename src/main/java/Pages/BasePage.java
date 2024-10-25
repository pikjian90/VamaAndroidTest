package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;

import java.util.List;

public class BasePage {
    public static AndroidDriver driver;

    public void setDriver(AndroidDriver driver){
        BasePage.driver = driver;
    }
    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    public void click(By locator){
        WebElement webElement = find(locator);
        webElement.click();
    }

    public void set(By locator, String text){
        WebElement webElement = find(locator);
        webElement.sendKeys(text);
    }





}

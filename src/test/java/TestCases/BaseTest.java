package TestCases;

import Pages.BasePage;
import Pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    public DesiredCapabilities cap;
    public static AndroidDriver mobileDriver;
    public static String appiumServer = "127.0.0.1";
    public static int appiumPort = 4723;
    public static URI appiumURL = null;
    public BasePage basePage;
    public LoginPage loginPage;
    String androidDeviceName = null;
    String androidUdId = null;
    String androidPlatformName = null;
    String androidPlatformVersion = null;
    String androidAppPackage = null;
    String androidAppActivity = null;
    String androidIgnoreHiddenApiPolicyError = null;

    public static Properties prop;

    public void setProperties(){
        prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fis);

            androidDeviceName = prop.getProperty("AndroidDeviceName");
            androidUdId = prop.getProperty("AndroidUdId");
            androidPlatformName = prop.getProperty("AndroidPlatformName");
            androidPlatformVersion = prop.getProperty("AndroidPlatformVersion");
            androidAppPackage = prop.getProperty("AndroidAppPackage");
            androidAppActivity = prop.getProperty("AndroidAppActivity");
            androidIgnoreHiddenApiPolicyError = prop.getProperty("AndroidIgnoreHiddenApiPolicyError");
            appiumURL=new URI(prop.getProperty("AppiumUrl"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        setProperties();

        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", androidDeviceName);
        cap.setCapability("udid", androidUdId);
        cap.setCapability("platformName", androidPlatformName);
        cap.setCapability("platformVersion", androidPlatformVersion);
        cap.setCapability("appPackage", androidAppPackage);
        cap.setCapability("appActivity", androidAppActivity);
        cap.setCapability("appium:ignoreHiddenApiPolicyError", androidIgnoreHiddenApiPolicyError);
        mobileDriver = new AndroidDriver(appiumURL.toURL(), cap);

        basePage = new BasePage();
        loginPage = new LoginPage();

        basePage.setDriver(mobileDriver);
        loginPage.setDriver(mobileDriver);
    }

    @AfterMethod
    public void tearDown(){
        mobileDriver.quit();
    }
}

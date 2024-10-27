package TestCases;

import Pages.BasePage;
import Pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class BaseTest {
    public DesiredCapabilities cap;
    public static AndroidDriver mobileDriver;
    public BasePage basePage;
    public LoginPage loginPage;
    String androidDeviceName = null;
    String androidUdId = null;
    String androidPlatformName = null;
    String androidPlatformVersion = null;
    String androidAppPackage = null;
    String androidAppActivity = null;
    String androidIgnoreHiddenApiPolicyError = null;
    String appiumUrl = null;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public static org.apache.log4j.Logger logger;


    public static Properties prop;

    public void setProperties(){
        prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                    + "/src/test/resources/config.properties");
            prop.load(fis);

            androidDeviceName = prop.getProperty("AndroidDeviceName");
            androidUdId = prop.getProperty("AndroidUdId");
            androidPlatformName = prop.getProperty("AndroidPlatformName");
            androidPlatformVersion = prop.getProperty("AndroidPlatformVersion");
            androidAppPackage = prop.getProperty("AndroidAppPackage");
            androidAppActivity = prop.getProperty("AndroidAppActivity");
            androidIgnoreHiddenApiPolicyError = prop.getProperty("AndroidIgnoreHiddenApiPolicyError");
            appiumUrl=prop.getProperty("AppiumUrl");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String takeScreenshot() {
        String base64ScreenshotCode = ((TakesScreenshot)mobileDriver).getScreenshotAs(OutputType.BASE64);
        return base64ScreenshotCode;
    }


    @BeforeSuite
    public void beforeSuite() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Report_" + dataFormat.format(timestamp) + ".html");
        htmlReporter.config().setDocumentTitle("Vama Android Testing Report");
        htmlReporter.config().setReportName("Smoke Test");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("deviceName","SM-N975F/DS");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester","QA");

        logger = Logger.getLogger("VamaAndroid");
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
        logger.setLevel(Level.INFO);
        logger.info("Before Suite");
    }

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {
        logger.info("Before Method");

        setProperties();

        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", androidDeviceName);
        cap.setCapability("udid", androidUdId);
        cap.setCapability("platformName", androidPlatformName);
        cap.setCapability("platformVersion", androidPlatformVersion);
        cap.setCapability("appPackage", androidAppPackage);
        cap.setCapability("appActivity", androidAppActivity);
        cap.setCapability("appium:ignoreHiddenApiPolicyError", androidIgnoreHiddenApiPolicyError);
        mobileDriver = new AndroidDriver(new URI(appiumUrl).toURL(), cap);

        basePage = new BasePage();
        loginPage = new LoginPage();

        basePage.setDriver(mobileDriver);
        loginPage.setDriver(mobileDriver);

    }

    @AfterMethod
    public void tearDown(){
        logger.info("After Method");

        mobileDriver.quit();
    }

    @AfterSuite
    public void tearDownSuite(){
        logger.info("After Suite");

        extentReports.flush();

    }
}

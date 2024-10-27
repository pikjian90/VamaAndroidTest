package TestCases;

import Pages.PhoneNumberPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PhoneNumberTest extends BaseTest{

    @Test(priority = 3)
    public void testInvalidCountryCode() {
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testInvalidCountryCode");

        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
            phoneNumberPage.setPhoneCode("");
            phoneNumberPage.setPhoneNumber("");

            String actualCountryCode = phoneNumberPage.getInvalidCountryCode();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(actualCountryCode, "Invalid country code");;

            Thread.sleep(2000);
            phoneNumberPage.clickSubmitButton();

            String actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();
            softAssert.assertTrue(actualValidationMessage.contains("Please provide valid Country Code"));
            softAssert.assertAll();
        }
        catch (Exception e){
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }
    }

    @Test(priority = 2)
    public void testEmptyPhoneNumber(){
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testEmptyPhoneNumber");
        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
            phoneNumberPage.setPhoneNumber("");
            phoneNumberPage.clickSubmitButton();
            String actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(actualValidationMessage, "This is not a valid phone number");
            softAssert.assertAll();
        }
        catch (Exception e){
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }


    }

    @Test(priority = 1)
    public void testOnceInvalidPhoneNumber(){
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testOnceInvalidPhoneNumber");

        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
            phoneNumberPage.setPhoneNumber("0000000000000");
            phoneNumberPage.clickSubmitButton();
            String actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(actualValidationMessage, "3009 - invalid phone number");
            softAssert.assertAll();
        }
        catch (Exception e){
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }

    }

    @Test(priority = 4)
    public void testMoreThan3TimesInvalidPhoneNumber(){
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testOnceInvalidPhoneNumber");

        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
            phoneNumberPage.setPhoneNumber("0000000000000");

            String actualValidationMessage = null;
            for (int i = 0; i < 4; i++) {
                phoneNumberPage.clickSubmitButton();
                actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();
            }

            SoftAssert softAssert = new SoftAssert();
            logger.info(actualValidationMessage);
            softAssert.assertTrue(actualValidationMessage.contains("Integrity API error"));
            softAssert.assertAll();
        }
        catch (Exception e){
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }
    }
}

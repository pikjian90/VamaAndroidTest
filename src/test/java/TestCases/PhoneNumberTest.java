package TestCases;

import Pages.PhoneNumberPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PhoneNumberTest extends BaseTest{

    @Test(priority = 3)
    public void testInvalidCountryCode() throws InterruptedException {
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

    @Test(priority = 2)
    public void testEmptyPhoneNumber(){
        PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
        phoneNumberPage.setPhoneNumber("");
        phoneNumberPage.clickSubmitButton();
        String actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualValidationMessage, "This is not a valid phone number");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void testOnceInvalidPhoneNumber(){
        PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
        phoneNumberPage.setPhoneNumber("0000000000000");
        phoneNumberPage.clickSubmitButton();
        String actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualValidationMessage, "3009 - invalid phone number");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testMoreThan3TimesInvalidPhoneNumber(){
        PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();
        phoneNumberPage.setPhoneNumber("0000000000000");

        String actualValidationMessage = null;
        for (int i = 0; i < 4; i++) {
            phoneNumberPage.clickSubmitButton();
            actualValidationMessage = phoneNumberPage.getValidationTextViewMessage();
        }
        
        SoftAssert softAssert = new SoftAssert();
        System.out.println(actualValidationMessage);
        softAssert.assertTrue(actualValidationMessage.contains("Integrity API error"));
        softAssert.assertAll();
    }
}

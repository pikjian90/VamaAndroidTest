package TestCases;

import Pages.PhoneNumberPage;
import Pages.SelectStatePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

public class SelectStateTest extends BaseTest {
    @Test
    public void testSearchedStateResultListContainsKeyword() throws InterruptedException {
        PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();

        SelectStatePage selectStatePage = phoneNumberPage.gotoSelectStatePage();
        selectStatePage.searchState("sin");
        List<WebElement> webElementList = selectStatePage.getSearchedStateList();

        SoftAssert softAssert = new SoftAssert();
        for (WebElement webElement : webElementList) {
            String stateName = webElement.getText().toLowerCase();
            System.out.println(stateName);
            softAssert.assertTrue(stateName.contains("sin"));
        }
        softAssert.assertAll();
    }

    @Test
    public void testSelectStateByScrolling() throws InterruptedException {
        PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();

        SelectStatePage selectStatePage = phoneNumberPage.gotoSelectStatePage();
        selectStatePage.scrollDownAndSelectStateText("Hong Kong");

        String selectedCountry = phoneNumberPage.getCountryName();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(selectedCountry.contains("Hong Kong"));
        softAssert.assertAll();
    }

    @Test
    public void testAllStatesSize() throws InterruptedException {
        PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();

        SelectStatePage selectStatePage = phoneNumberPage.gotoSelectStatePage();
        Set<String> allStateList = selectStatePage.scrollDownToStateTextAndGetStateList("Zimbabwe");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(allStateList.size() >= 230);
        softAssert.assertAll();
    }
}

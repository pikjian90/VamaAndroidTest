package TestCases;

import Pages.PhoneNumberPage;
import Pages.SelectStatePage;
import Utils.XLUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class SelectStateTest extends BaseTest {
    @DataProvider(name="StateTestData")
    public Object[][] getData() throws IOException {
        //read data from excel
        String path = "/Users/pikjian/IdeaProjects/VamaAndroidTest/src/test/resources/StateTestData.xlsx";
        int rowNum = XLUtils.getRowCount(path,"Set1");
        int colNum = XLUtils.getCellCount(path,"Set1",1);
        String[][] testData = new String [rowNum][colNum];

        for(int i=1;i<=rowNum;i++){
            for(int j=0;j<colNum;j++){
                testData[i-1][j] = XLUtils.getCellData(path,"Set1",i,j);
            }
        }
        return testData;
    }

    @Test(dataProvider = "StateTestData")
    public void testSearchedStateResultListContainsKeyword(String stateNameKeyword) throws InterruptedException {
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testSearchedStateResultListContainsKeyword");

        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();

            SelectStatePage selectStatePage = phoneNumberPage.gotoSelectStatePage();
            selectStatePage.searchState(stateNameKeyword);
            List<WebElement> webElementList = selectStatePage.getSearchedStateList();

            SoftAssert softAssert = new SoftAssert();
            for (WebElement webElement : webElementList) {
                String stateName = webElement.getText().toLowerCase();
                logger.info(stateName);
                softAssert.assertTrue(stateName.contains(stateNameKeyword));
            }
            softAssert.assertAll();
        }
        catch (Exception e){
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }
    }

    @Test
    public void testSelectStateByScrolling() throws InterruptedException {
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testSelectStateByScrolling");

        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();

            SelectStatePage selectStatePage = phoneNumberPage.gotoSelectStatePage();
            selectStatePage.scrollDownAndSelectStateText("Hong Kong");

            String selectedCountry = phoneNumberPage.getCountryName();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(selectedCountry.contains("Hong Kong"));
            softAssert.assertAll();
        }
        catch (Exception e){
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }
    }

    @Test
    public void testAllStatesSize() throws InterruptedException {
        ExtentTest extentTest = extentReports.createTest(this.getClass().getName()
                + "_testSelectStateByScrolling");

        try{
            PhoneNumberPage phoneNumberPage = loginPage.goToPhoneNumberPage();

            SelectStatePage selectStatePage = phoneNumberPage.gotoSelectStatePage();
            Set<String> allStateList = selectStatePage.scrollDownToStateTextAndGetStateList("Zimbabwe");

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(allStateList.size() >= 230);
            softAssert.assertAll();
        }
        catch (Exception e) {
            extentTest.log(Status.FAIL, e.getMessage());
            extentTest.addScreenCaptureFromBase64String(takeScreenshot());
        }
    }
}

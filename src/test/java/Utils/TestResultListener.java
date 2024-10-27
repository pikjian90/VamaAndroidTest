package Utils;

import Pages.BasePage;
import TestCases.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener extends BaseTest implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test Case Failed: " + result.getName());
    }
}

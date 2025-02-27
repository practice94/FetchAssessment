package Utils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    @BeforeMethod
    public void logTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ValidationUtil.log("\nStarting test: " + testName);
    }

    @AfterMethod
    public void logTestEnd(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.getStatus()==1)
            ValidationUtil.log("Finished test: " + testName + " - Status: PASSED");
        else
            ValidationUtil.log("Finished test: " + testName + " - Status: FAILED");

        ValidationUtil.log("---------------------------------------------");

    }
}

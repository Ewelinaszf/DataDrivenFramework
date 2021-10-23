package base.listeners;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtils;

import java.io.IOException;
import java.util.Locale;

public class CustomListeners extends TestBase implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(LogStatus.PASS, result.getName().toUpperCase(Locale.ROOT)+" PASS");
        extent.endTest(extentTest);
        extent.flush();
    }
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.startTest(result.getName().toUpperCase(Locale.ROOT));
    }


    @Override
    public void onTestFailure(ITestResult result) {
        System.setProperty("org.uncommons.reportng.escape-output","false");
        try {
            TestUtils.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        extentTest.log(LogStatus.FAIL, result.getName().toUpperCase(Locale.ROOT)+" FAILED with exception: "+ result.getThrowable());
        extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(TestUtils.screenshotName2));

        Reporter.log("<a target=\"_blank\" href="+ TestUtils.screenshotName2+">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href="+ TestUtils.screenshotName2+"><img src="+ TestUtils.screenshotName2+" height=200 width=200></img></a>");
        extent.endTest(extentTest);
        extent.flush();

    }



    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
      
    }
}

package base.listeners;

import base.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtil;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {

            TestUtil.captureScreenshot();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
        System.out.println("HIiiiiiiiiiiiiiiiiiiiiiiiiiii");
        Reporter.log("<br");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src=\"C:\\Selenium\\error.jpg\" height=200 width=200></img></a>");
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

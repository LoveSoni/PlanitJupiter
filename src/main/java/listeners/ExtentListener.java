package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import utilities.loggerutilities.LogUtility;
import utilities.reportingUtilities.ExtentManager;
import utilities.reportingUtilities.ExtentReport;

public class ExtentListener extends TestListenerAdapter implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.init();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtility.pass(result.getMethod().getMethodName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtility.error(result.getMethod().getMethodName() + " is failed");
        WebDriver driver = (WebDriver)result.getTestContext().getAttribute("WebDriver");
        String base64Image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        ExtentManager.getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}

package org.core.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.SneakyThrows;
import org.core.driver.Browser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportListener implements ITestListener {
    ExtentReports reporter = new ExtentReports();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static ExtentTest test;
    public static ExtentTest getReporter(){
        return extentTest.get();
    }
    @Override
    public synchronized void onTestStart(ITestResult result) {
        test = reporter.createTest(result.getTestClass().getName());
        extentTest.set(test);
    }
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Successful</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
        extentTest.remove();
    }
    @SneakyThrows
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        //add screenshot for failed test.
        File src = ((TakesScreenshot) new Browser().getDriver()).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);
        String screenshotPath = System.getProperty("user.dir")+ "/test-output/"+actualDate+".jpeg";
        File dest = new File(screenshotPath);
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
        extentTest.get().log(Status.FAIL, result.getThrowable());
        extentTest.get().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
        extentTest.remove();
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
        extentTest.remove();
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public synchronized void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        String suite = context.getCurrentXmlTest().getSuite().getName();
        System.out.println(suite + " Test Suite Started!");
        String path = System.getProperty("user.dir") + "/test-output/" + suite + "Report.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle(suite);
        spark.config().setEncoding("utf-8");
        spark.config().setReportName("Test Report");
        spark.config().setTimelineEnabled(true);
        reporter.attachReporter(spark);
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        if(reporter!=null)
            reporter.flush();
    }
}

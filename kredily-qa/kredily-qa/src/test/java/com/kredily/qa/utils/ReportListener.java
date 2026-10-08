package com.kredily.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.kredily.qa.base.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Produces reports/extent-report.html with pass/fail status and screenshots on failure. */
public class ReportListener implements ITestListener {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> CURRENT = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext ctx) {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
        spark.config().setDocumentTitle("Kredily HRMS - Mobile Automation Report");
        spark.config().setReportName("Appium + TestNG Execution Report");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Device", ConfigReader.get("device.name"));
        extent.setSystemInfo("Platform", ConfigReader.get("platform.name"));
    }

    @Override
    public void onTestStart(ITestResult r) {
        String name = r.getMethod().getMethodName();
        Object[] params = r.getParameters();
        if (params != null && params.length > 0) name += " " + java.util.Arrays.toString(params);
        CURRENT.set(extent.createTest(name, r.getMethod().getDescription()));
    }

    @Override
    public void onTestSuccess(ITestResult r) {
        CURRENT.get().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult r) {
        CURRENT.get().fail(r.getThrowable());
        Object inst = r.getInstance();
        if (inst instanceof BaseTest bt && bt.getDriver() != null) {
            String shot = ScreenshotUtil.capture(bt.getDriver(), r.getMethod().getMethodName());
            if (shot != null) CURRENT.get().fail("Screenshot").addScreenCaptureFromPath(shot);
        }
    }

    @Override
    public void onTestSkipped(ITestResult r) {
        CURRENT.get().skip(r.getThrowable() == null ? "Skipped" : r.getThrowable().toString());
    }

    @Override
    public void onFinish(ITestContext ctx) {
        extent.flush();
    }
}

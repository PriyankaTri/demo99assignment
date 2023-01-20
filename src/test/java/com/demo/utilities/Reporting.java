package com.demo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Reporting implements ITestListener {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ITestResult result;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\myreport.html");
        htmlReporter.config().setDocumentTitle("Demo Automation Report");
        htmlReporter.config().setReportName("Demo Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Hostname","Localhost");
        extentReports.setSystemInfo("Browser","chrome");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.SUCCESS)
            extentTest.log(Status.PASS,"Test case is passed"+iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
            extentTest.log(Status.FAIL,"Test case is failed"+iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.SKIP)
            extentTest.log(Status.SKIP,"Test case is skipped"+iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Test Execution started :" +iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test Execution finished :" +iTestContext.getName());
        extentReports.flush();

    }
}

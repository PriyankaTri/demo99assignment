package com.demo.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Started.." +iTestResult.getName());

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test is Passed :" +iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test is failed :" +iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test is Skipped :" +iTestResult.getName());
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
    }
}

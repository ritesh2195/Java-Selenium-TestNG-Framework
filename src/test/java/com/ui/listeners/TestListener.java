package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    public void onTestStart(ITestResult result) {

        logger.info(result.getMethod().getMethodName());

        logger.info(result.getMethod().getDescription());

        logger.info(Arrays.toString(result.getMethod().getGroups()));

        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {

        logger.info(result.getMethod().getMethodName() + " " + "PASSED");

        ExtentReporterUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    public void onTestFailure(ITestResult result) {

        logger.error(result.getMethod().getMethodName() + " " + "FAILED");

        logger.error(result.getThrowable().getMessage());

        ExtentReporterUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");

        ExtentReporterUtility.getExtentTest().log(Status.FAIL,result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {

        logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
    }

    public void onStart(ITestContext context) {

        logger.info("Test suite started");

        ExtentReporterUtility.setupSparkReporter("report");
    }

    public void onFinish(ITestContext context) {

        logger.info("Test suite completed");

        ExtentReporterUtility.flushReport();
    }
}

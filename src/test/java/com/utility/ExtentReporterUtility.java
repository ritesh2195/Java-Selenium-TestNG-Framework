package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void setupSparkReporter(String reportName){
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("Reports//"+reportName+".html");

        extentReports = new ExtentReports();

        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName){

        ExtentTest extentTest = extentReports.createTest(testName);

        extentTestThreadLocal.set(extentTest);
    }

    public static ExtentTest getExtentTest(){

        return extentTestThreadLocal.get();
    }

    public static void flushReport(){

        extentReports.flush();
    }
}

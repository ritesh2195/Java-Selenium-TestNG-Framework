package com.ui.listeners;

import com.utility.ConfigReaderUtility;
import com.utility.JSONUtility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {

    private static int initialCount = 1;

    //private static final int MAX_COUNT = Integer.parseInt(ConfigReaderUtility.getInstance().getPropertyValue("MAX_COUNT"));

    private static final int MAX_COUNT_ATTEMPT = JSONUtility.readJson().getMAX_COUNT();

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (initialCount<=MAX_COUNT_ATTEMPT){

            initialCount++;

            return true;
        }
        return false;
    }
}

package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RemoteTestUtility {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();
    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<DesiredCapabilities> capabilitiesThreadLocal = new ThreadLocal<DesiredCapabilities>();

    public static WebDriver initializeLambdaTestSession(String browserName, String testName){

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName",browserName);

        capabilities.setCapability("browserVersion","127");

        Map<String,Object> ltOption = new HashMap<>();

        ltOption.put("user",ConfigReaderUtility.getInstance().getPropertyValue("USER"));

        ltOption.put("accessKey",ConfigReaderUtility.getInstance().getPropertyValue("ACCESS_KEY"));

        ltOption.put("build","Selenium 4");

        ltOption.put("name",testName);

        ltOption.put("plantformName","Windows 11");

        ltOption.put("seCdp",true);

        ltOption.put("selenium_version","4.25.0");

        capabilities.setCapability("LT:Options",ltOption);

        capabilitiesThreadLocal.set(capabilities);

        try {

            WebDriver driver = new RemoteWebDriver(new URL(HUB_URL),capabilitiesThreadLocal.get());

            driver.get(JSONUtility.readJson().getUrl());

            driverThreadLocal.set(driver);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        return driverThreadLocal.get();
    }

    public static void quitSession(){

        driverThreadLocal.get().quit();
    }
}

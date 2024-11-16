package com.factory;

import com.constants.BrowserType;
import com.utility.ConfigReaderUtility;
import com.utility.JSONUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {


    public WebDriver driver;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the thradlocal driver on the basis of given
     * browser
     *
     * @param browserName
     * @return this will return tldriver.
     */
    public WebDriver init_driver(String browserName) {

        BrowserType browserType = BrowserType.fromString(browserName);

        switch (browserType) {
            case CHROME:

                tlDriver.set(new ChromeDriver());

                break;
            case FIREFOX:

                tlDriver.set(new FirefoxDriver());

                break;
            case SAFARI:

                tlDriver.set(new SafariDriver());

                break;
            default:
                System.out.println("Please pass the correct browser value: " + browserName);
                break;
        }

        getDriver().manage().deleteAllCookies();

        getDriver().manage().window().maximize();

        getDriver().get(JSONUtility.readJson().getUrl());

        return getDriver();

    }

    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}

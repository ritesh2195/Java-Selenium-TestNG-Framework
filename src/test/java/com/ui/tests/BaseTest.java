package com.ui.tests;

import com.factory.BrowserFactory;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;
import com.utility.ConfigReaderUtility;
import com.utility.RemoteTestUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    boolean isRemoteExecution = false;

    @BeforeMethod
    public void setUp(ITestResult result){

        WebDriver driver = null;

        isRemoteExecution = Boolean.parseBoolean(ConfigReaderUtility.getInstance()
                .getPropertyValue("IS_REMOTE_EXECUTION"));

        if (isRemoteExecution){

            driver = RemoteTestUtility.initializeLambdaTestSession(ConfigReaderUtility.getInstance()
                    .getPropertyValue("BROWSER"),result.getMethod().getMethodName());

        } else {

            BrowserFactory factory = new BrowserFactory();

            driver = factory.init_driver(ConfigReaderUtility.getInstance()
                    .getPropertyValue("BROWSER"),Boolean.parseBoolean(ConfigReaderUtility
                    .getInstance().getPropertyValue("IS_HEADLESS")));
        }

        homePage = new HomePage(driver);

        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown(){

        if (isRemoteExecution){

            RemoteTestUtility.quitSession();
        }
        else {

            BrowserFactory.getDriver().quit();
        }
    }

    public BrowserUtility getInstance(){

        return homePage;
    }
}

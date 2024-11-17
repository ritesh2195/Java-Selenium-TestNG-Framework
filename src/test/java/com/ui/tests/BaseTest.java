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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    boolean isRemoteExecution = false;

    @Parameters({"browserName","isRemoteExecution","isHeadless"})
    @BeforeMethod
    public void setUp(
            @Optional("chrome") String browserName,
            @Optional("false") boolean isRemoteExecution,
            @Optional("false") boolean isHeadless,
                      ITestResult result){

        WebDriver driver = null;

        this.isRemoteExecution = isRemoteExecution;

        if (isRemoteExecution){

            driver = RemoteTestUtility.initializeLambdaTestSession(browserName,result.getMethod()
                    .getMethodName());

        } else {

            BrowserFactory factory = new BrowserFactory();

            driver = factory.init_driver(browserName,isHeadless);
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

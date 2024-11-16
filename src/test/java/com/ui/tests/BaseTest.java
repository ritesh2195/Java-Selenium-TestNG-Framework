package com.ui.tests;

import com.factory.BrowserFactory;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;
import com.utility.ConfigReaderUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){

        BrowserFactory factory = new BrowserFactory();

        factory.init_driver(ConfigReaderUtility.getInstance().getPropertyValue("BROWSER"));

        homePage = new HomePage(BrowserFactory.getDriver());

        loginPage = new LoginPage(BrowserFactory.getDriver());
    }

    @AfterMethod
    public void tearDown(){

        BrowserFactory.getDriver().quit();
    }

    public BrowserUtility getInstance(){

        return homePage;
    }
}

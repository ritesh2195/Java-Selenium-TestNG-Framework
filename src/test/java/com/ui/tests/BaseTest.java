package com.ui.tests;

import com.factory.BrowserFactory;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){

        BrowserFactory factory = new BrowserFactory();

        factory.init_driver("chrome");

        homePage = new HomePage(BrowserFactory.getDriver());

        loginPage = new LoginPage(BrowserFactory.getDriver());
    }

    @AfterMethod
    public void tearDown(){

        BrowserFactory.getDriver().quit();
    }
}

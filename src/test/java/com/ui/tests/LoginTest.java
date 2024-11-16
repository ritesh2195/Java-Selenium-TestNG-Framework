package com.ui.tests;

import com.pojo.User;
import com.ui.dataProvider.LoginDataProvider;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends BaseTest{

    @Test(description = "Verify valid user is able to login or not", groups = {"sanity"},
            dataProviderClass = com.ui.dataProvider.LoginDataProvider.class,
            dataProvider = "LoginDataProvider",retryAnalyzer = com.ui.listeners.TestRetryAnalyzer.class
    )
    public void validUserLoginTest(User user) {

        String userName = homePage.clickSignInLink().doLogin(user.getEmail(), user.getPassword())
                .getUserName();

        assertEquals(userName,"Ritesh Mishra");

    }


    @Test(description = "Verify invalid user is not able to login", groups = {"sanity"}
    )
    public void invalidUserLoginTest(){

        loginPage = homePage.clickSignInLink();

        loginPage.doLogin("random@gmail.com","password");

        assertTrue(loginPage.isAuthenticationErrorDisplay());
    }
}

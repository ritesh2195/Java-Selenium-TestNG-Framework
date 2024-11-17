package com.ui.pages;

import com.factory.BrowserFactory;
import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BrowserUtility {

    private By emailLocator = By.id("email");
    private By passwordLocator = By.id("passwd");
    private By signInLocator = By.id("SubmitLogin");
    private By authenticationFailedLocator= By.xpath("//li[contains(text(),'Authentication failed.')]");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public MyAccountPage doLogin(String email, String password){

        enterText(emailLocator,email);

        enterText(passwordLocator,password);

        clickOn(signInLocator);

        return new MyAccountPage(getDriver());
    }

    public boolean isAuthenticationErrorDisplayed(){
        return isElementVisible(authenticationFailedLocator);
    }
}

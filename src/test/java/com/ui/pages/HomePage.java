package com.ui.pages;

import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BrowserUtility {

    private By signInButton = By.xpath("//a[contains(text(),'Sign in')]");

    public HomePage(WebDriver driver){

        super(driver);
    }

    public LoginPage clickSignInLink(){

        clickOn(signInButton);

        return new LoginPage(getDriver());
    }
}

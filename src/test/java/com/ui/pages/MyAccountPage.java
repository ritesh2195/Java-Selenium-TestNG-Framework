package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BrowserUtility {

    private By userNameLocator = By.xpath("//a[@class='account']//span");

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    public String getUserName(){

        return getText(userNameLocator);
    }
}

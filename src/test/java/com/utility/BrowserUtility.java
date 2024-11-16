package com.utility;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BrowserUtility {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor javascriptExecutor;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver() {

        return driver;
    }

    public BrowserUtility(WebDriver driver) {
        super();

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        javascriptExecutor = ((JavascriptExecutor) driver);
    }

    public void launchURL(String url) {

        logger.info("Trying to launch application url");

        driver.get(url);
    }

    public void maximizeWindow() {

        logger.info("Maximizing browser window");

        driver.manage().window().maximize();
    }

    public void clickOn(By locator) {
        try {

            logger.info("Trying to click on element " + locator);

            driver.findElement(locator).click();

        } catch (NoSuchElementException | StaleElementReferenceException e) {

            logger.warn("Element was not visible");

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));

            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException e) {

            logger.warn("Element click was intercepted");

            javascriptExecutor.executeScript("argument[0].click()", driver.findElement(locator));
        } catch (ElementNotInteractableException e) {

            logger.warn("Element was not intractable");

            wait.until(ExpectedConditions.elementToBeClickable(locator));

            driver.findElement(locator).click();

        } catch (Exception e) {

            logger.error("unable to click on element " + locator);
        }
    }

    public void enterText(By locator, String text) {

        try {

            logger.info("Trying to enter text in " + locator);

            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {

            javascriptExecutor.executeScript("arguments[0].value=arguments[1];", locator, text);
        }

    }

    public String getText(By locator) {

        try {

            logger.info("Trying to get text from " + locator);

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));

            return driver.findElement(locator).getText();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "Exception occurred while retrieving the text";
    }

    public boolean isElementVisible(By locator) {

        return driver.findElement(locator).isDisplayed();
    }

    public void takeScreenshot(String name){

        TakesScreenshot screenshot = (TakesScreenshot)driver;

        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.copyFile(screenshotFile,new File("screenshots//"+name));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

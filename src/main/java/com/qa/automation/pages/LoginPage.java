package com.qa.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoginPage class implements the Page Object Model pattern for the login page.
 * Contains all the elements and actions related to the login functionality.
 */
public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the login page
     */
    public void navigateToLoginPage() {
        logger.info("Navigating to login page");
        driver.get("https://www.saucedemo.com/");
    }

    /**
     * Enters the username in the username field
     * @param username The username to enter
     */
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        usernameField.sendKeys(username);
    }

    /**
     * Enters the password in the password field
     * @param password The password to enter
     */
    public void enterPassword(String password) {
        logger.info("Entering password");
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the login button
     */
    public void clickLoginButton() {
        logger.info("Clicking login button");
        loginButton.click();
    }

    /**
     * Performs the complete login action
     * @param username The username to enter
     * @param password The password to enter
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Checks if the error message is displayed
     * @return true if the error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        logger.info("Checking if error message is displayed");
        return errorMessage.isDisplayed();
    }

    /**
     * Gets the error message text
     * @return The error message text
     */
    public String getErrorMessageText() {
        logger.info("Getting error message text");
        return errorMessage.getText();
    }
} 
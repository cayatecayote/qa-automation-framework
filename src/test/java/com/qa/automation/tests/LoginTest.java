package com.qa.automation.tests;

import com.qa.automation.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Authentication")
@Feature("Login")
@DisplayName("Login Tests")
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @Test
    @Story("Successful Login")
    @DisplayName("Should login successfully with valid credentials")
    @Description("Test that verifies successful login with valid username and password")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldLoginSuccessfully() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        assertThat(driver.getCurrentUrl())
            .as("Should be redirected to inventory page after successful login")
            .contains("/inventory.html");
    }

    @ParameterizedTest(name = "Login attempt with username: {0} and password: {1}")
    @CsvSource({
        "invalid_user, secret_sauce",
        "standard_user, wrong_password",
        "locked_out_user, secret_sauce"
    })
    @Story("Unsuccessful Login")
    @DisplayName("Should show error message with invalid credentials")
    @Description("Test that verifies error message is displayed when login fails")
    @Severity(SeverityLevel.NORMAL)
    public void shouldShowErrorMessageWithInvalidCredentials(String username, String password) {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(username, password);
        
        assertThat(loginPage.isErrorMessageDisplayed())
            .as("Error message should be displayed for invalid credentials")
            .isTrue();
            
        String errorMessage = loginPage.getErrorMessageText();
        if (username.equals("locked_out_user")) {
            assertThat(errorMessage)
                .as("Error message should indicate user is locked out")
                .contains("this user has been locked out");
        } else {
            assertThat(errorMessage)
                .as("Error message should indicate invalid credentials")
                .contains("Username and password do not match");
        }
    }
} 
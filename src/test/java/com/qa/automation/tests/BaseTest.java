package com.qa.automation.tests;

import com.qa.automation.utils.ScreenshotUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * Base test class that handles WebDriver initialization and common test setup.
 * All test classes should extend this class to inherit the WebDriver setup and teardown.
 */
public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        logger.info("Setting up WebDriver");
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        
        // Uncomment the following line for headless execution
        // options.addArguments("--headless");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    public void tearDown() {
        try {
            // Take screenshot after test execution
            String testName = this.getClass().getSimpleName() + "_" + 
                Thread.currentThread().getStackTrace()[2].getMethodName();
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName);
            
            // Attach screenshot to Allure report
            if (screenshotPath != null) {
                Path path = Paths.get(screenshotPath);
                Allure.addAttachment("Screenshot", Files.newInputStream(path));
            }
        } catch (IOException e) {
            logger.error("Failed to attach screenshot to Allure report", e);
        } finally {
            logger.info("Closing WebDriver");
            if (driver != null) {
                driver.quit();
            }
        }
    }
} 
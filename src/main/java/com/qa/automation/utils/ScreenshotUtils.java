package com.qa.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for taking screenshots during test execution
 */
public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "target/screenshots";

    /**
     * Takes a screenshot and saves it to the target/screenshots directory
     * @param driver The WebDriver instance
     * @param testName The name of the test
     * @return The path to the saved screenshot
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            // Generate timestamp for unique filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = String.format("%s_%s.png", testName, timestamp);
            Path screenshotPath = screenshotDir.resolve(fileName);

            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), screenshotPath);

            logger.info("Screenshot saved to: {}", screenshotPath);
            return screenshotPath.toString();
        } catch (IOException e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }
} 
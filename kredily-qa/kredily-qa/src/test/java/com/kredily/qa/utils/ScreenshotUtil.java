package com.kredily.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class ScreenshotUtil {
    private static final Path DIR = Paths.get("reports", "screenshots");

    private ScreenshotUtil() {}

    /** Saves a PNG and returns its path relative to reports/ (usable in the HTML report). */
    public static String capture(WebDriver driver, String name) {
        try {
            Files.createDirectories(DIR);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = DIR.resolve(name.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + System.currentTimeMillis() + ".png");
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            return "screenshots/" + dest.getFileName();
        } catch (Exception e) {
            return null;
        }
    }
}

package com.kredily.qa.utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Properties;

/** Resolves keys from locators.properties into Selenium/Appium By objects. */
public final class LocatorRepo {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = LocatorRepo.class.getClassLoader().getResourceAsStream("locators.properties")) {
            PROPS.load(in);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load locators.properties", e);
        }
    }

    private LocatorRepo() {}

    public static By by(String key) {
        String raw = PROPS.getProperty(key);
        if (raw == null) throw new IllegalArgumentException("Missing locator key: " + key);
        int idx = raw.indexOf('=');
        String strategy = raw.substring(0, idx).trim();
        String value = raw.substring(idx + 1).trim();
        return switch (strategy) {
            case "id" -> AppiumBy.id(value);
            case "acc" -> AppiumBy.accessibilityId(value);
            case "xpath" -> AppiumBy.xpath(value);
            case "text" -> AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + value + "\")");
            default -> throw new IllegalArgumentException("Unknown strategy '" + strategy + "' for key " + key);
        };
    }
}

package com.kredily.qa.pages;

import com.kredily.qa.utils.ConfigReader;
import com.kredily.qa.utils.LocatorRepo;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final AndroidDriver driver;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
    }

    protected By loc(String key) {
        return LocatorRepo.by(key);
    }

    private WebDriverWait wait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    protected WebElement waitVisible(String key) {
        return wait(ConfigReader.getInt("wait.explicit"))
                .until(ExpectedConditions.visibilityOfElementLocated(loc(key)));
    }

    protected void tap(String key) {
        wait(ConfigReader.getInt("wait.explicit"))
                .until(ExpectedConditions.elementToBeClickable(loc(key))).click();
    }

    protected void type(String key, String text) {
        WebElement el = waitVisible(key);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    protected String textOf(String key) {
        return waitVisible(key).getText();
    }

    /** Short, non-throwing visibility check. */
    protected boolean isShown(String key) {
        try {
            return wait(ConfigReader.getInt("wait.short"))
                    .until(ExpectedConditions.visibilityOfElementLocated(loc(key))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Taps an optional pop-up (permission / onboarding) only if it appears. */
    protected void dismissIfPresent(String key) {
        if (isShown(key)) tap(key);
    }

    public void hideKeyboardQuietly() {
        try { driver.hideKeyboard(); } catch (Exception ignored) { }
    }
}

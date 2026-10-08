package com.kredily.qa.base;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected AndroidDriver driver;

    public AndroidDriver getDriver() {
        return driver;
    }

    /** Fresh app session per test (noReset=false) so tests never depend on each other. */
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = DriverFactory.create();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) { }
        }
    }
}

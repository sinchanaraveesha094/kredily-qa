package com.kredily.qa.base;

import com.kredily.qa.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {}

    public static AndroidDriver create() throws Exception {
        UiAutomator2Options opts = new UiAutomator2Options()
                .setPlatformName(ConfigReader.get("platform.name"))
                .setDeviceName(ConfigReader.get("device.name"))
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(180))
                .setAppWaitActivity("*")
                .setNoReset(false);

        String apk = ConfigReader.get("apk.path");
        if (!apk.isEmpty() && new File(apk).exists()) {
            opts.setApp(new File(apk).getAbsolutePath());
        }
        if (!ConfigReader.get("app.package").isEmpty()) opts.setAppPackage(ConfigReader.get("app.package"));
        if (!ConfigReader.get("app.activity").isEmpty()) opts.setAppActivity(ConfigReader.get("app.activity"));

        AndroidDriver driver = new AndroidDriver(new URL(ConfigReader.get("appium.url")), opts);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO); // explicit waits only
        return driver;
    }
}

package com.kredily.qa.pages;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
        dismissIfPresent("popup.skip");
        dismissIfPresent("popup.allow");
    }

    public boolean isDisplayed() {
        return isShown("login.email") && isShown("login.submit");
    }

    public LoginPage enterEmail(String email) {
        type("login.email", email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type("login.password", password);
        hideKeyboardQuietly();
        return this;
    }

    public void tapLogin() {
        tap("login.submit");
    }

    public DashboardPage loginAs(String email, String password) {
        enterEmail(email).enterPassword(password).tapLogin();
        return new DashboardPage(driver);
    }

    public boolean isErrorShown() {
        return isShown("login.error");
    }

    public String errorText() {
        return textOf("login.error");
    }
}

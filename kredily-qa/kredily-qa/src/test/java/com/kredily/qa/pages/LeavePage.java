package com.kredily.qa.pages;

import io.appium.java_client.android.AndroidDriver;

public class LeavePage extends BasePage {

    public LeavePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isBalanceShown() {
        return isShown("leave.balance");
    }

    public LeavePage startApplication() {
        tap("leave.apply");
        return this;
    }

    public LeavePage selectFirstLeaveType() {
        tap("leave.type");
        tap("leave.type.first");
        return this;
    }

    public LeavePage enterReason(String reason) {
        type("leave.reason", reason);
        hideKeyboardQuietly();
        return this;
    }

    public void submit() {
        tap("leave.submit");
    }

    public boolean isValidationErrorShown() {
        return isShown("leave.error");
    }
}

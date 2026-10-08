package com.kredily.qa.pages;

import io.appium.java_client.android.AndroidDriver;

public class AttendancePage extends BasePage {

    public AttendancePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isCheckInAvailable() {
        return isShown("att.checkin");
    }

    public boolean isCheckOutAvailable() {
        return isShown("att.checkout");
    }

    public void tapCheckIn() {
        tap("att.checkin");
        dismissIfPresent("popup.allow"); // location permission prompt
    }

    public void tapCheckOut() {
        tap("att.checkout");
    }

    public boolean isSuccessShown() {
        return isShown("att.success");
    }
}

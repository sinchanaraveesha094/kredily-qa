package com.kredily.qa.pages;

import io.appium.java_client.android.AndroidDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        dismissIfPresent("popup.allow");
        dismissIfPresent("popup.skip");
        return isShown("dash.marker");
    }

    public boolean isGreetingShown() {
        return isShown("dash.greeting");
    }

    public boolean areNavItemsShown() {
        return isShown("dash.nav.attendance") && isShown("dash.nav.leave") && isShown("dash.nav.profile");
    }

    public AttendancePage openAttendance() {
        tap("dash.nav.attendance");
        return new AttendancePage(driver);
    }

    public LeavePage openLeave() {
        tap("dash.nav.leave");
        return new LeavePage(driver);
    }
}

package com.kredily.qa.tests;

import com.kredily.qa.base.BaseTest;
import com.kredily.qa.pages.DashboardPage;
import com.kredily.qa.pages.LoginPage;
import com.kredily.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Journey 3: dashboard validation. */
public class DashboardTest extends BaseTest {

    private DashboardPage loginAndGetDashboard() {
        return new LoginPage(driver).loginAs(
                ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));
    }

    @Test(description = "J3 - Dashboard shows greeting and primary navigation after login")
    public void dashboard_showsGreetingAndNavigation() {
        DashboardPage dashboard = loginAndGetDashboard();
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard should load");
        Assert.assertTrue(dashboard.isGreetingShown(), "Greeting / user context should be visible");
        Assert.assertTrue(dashboard.areNavItemsShown(), "Attendance, Leave and Profile entries should be visible");
    }

    @Test(description = "J3b - Dashboard survives a rotate-and-back (state is not lost)")
    public void dashboard_survivesOrientationChange() {
        DashboardPage dashboard = loginAndGetDashboard();
        Assert.assertTrue(dashboard.isLoaded());
        try {
            driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
            driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
        } finally {
            driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
        }
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard should still be displayed after rotation");
    }
}

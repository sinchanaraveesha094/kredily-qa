package com.kredily.qa.tests;

import com.kredily.qa.base.BaseTest;
import com.kredily.qa.pages.DashboardPage;
import com.kredily.qa.pages.LoginPage;
import com.kredily.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Journey 1: valid login. */
public class LoginTest extends BaseTest {

    @Test(description = "J1 - Valid credentials land the user on the dashboard")
    public void validLogin_landsOnDashboard() {
        LoginPage login = new LoginPage(driver);
        Assert.assertTrue(login.isDisplayed(), "Login screen should be visible on a fresh install");

        DashboardPage dashboard = login.loginAs(
                ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));

        Assert.assertTrue(dashboard.isLoaded(), "Dashboard should load after a valid login");
    }
}

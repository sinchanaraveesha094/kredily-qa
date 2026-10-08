package com.kredily.qa.tests;

import com.kredily.qa.base.BaseTest;
import com.kredily.qa.pages.DashboardPage;
import com.kredily.qa.pages.LeavePage;
import com.kredily.qa.pages.LoginPage;
import com.kredily.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Journey 5: leave workflow. */
public class LeaveTest extends BaseTest {

    private LeavePage openLeave() {
        DashboardPage dash = new LoginPage(driver).loginAs(
                ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));
        Assert.assertTrue(dash.isLoaded(), "Dashboard should load before opening Leave");
        return dash.openLeave();
    }

    @Test(description = "J5 - Leave screen displays the user's leave balance")
    public void leave_showsBalance() {
        Assert.assertTrue(openLeave().isBalanceShown(), "Leave balance should be visible");
    }

    @Test(description = "J5b - Submitting a leave request with missing mandatory data is blocked with validation")
    public void leave_applyWithoutRequiredFields_showsValidation() {
        LeavePage leave = openLeave().startApplication();
        leave.submit();
        Assert.assertTrue(leave.isValidationErrorShown(),
                "Submitting an empty leave form must show a validation message");
    }
}

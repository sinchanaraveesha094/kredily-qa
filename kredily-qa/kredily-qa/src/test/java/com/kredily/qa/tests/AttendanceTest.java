package com.kredily.qa.tests;

import com.kredily.qa.base.BaseTest;
import com.kredily.qa.pages.AttendancePage;
import com.kredily.qa.pages.DashboardPage;
import com.kredily.qa.pages.LoginPage;
import com.kredily.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Journey 4: attendance check-in. */
public class AttendanceTest extends BaseTest {

    private AttendancePage openAttendance() {
        DashboardPage dash = new LoginPage(driver).loginAs(
                ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));
        Assert.assertTrue(dash.isLoaded(), "Dashboard should load before opening Attendance");
        return dash.openAttendance();
    }

    @Test(description = "J4 - User can check in and the UI reflects the new state")
    public void checkIn_updatesAttendanceState() {
        AttendancePage attendance = openAttendance();
        // Account may already be checked-in from an earlier run; handle both starting states.
        if (attendance.isCheckInAvailable()) {
            attendance.tapCheckIn();
            Assert.assertTrue(attendance.isSuccessShown() || attendance.isCheckOutAvailable(),
                    "After check-in, a success message or a Check-Out action should appear");
        } else {
            Assert.assertTrue(attendance.isCheckOutAvailable(),
                    "If Check-In is not offered, Check-Out must be (user is already punched in)");
        }
    }

    @Test(description = "J4b - Check-In and Check-Out are never offered at the same time")
    public void attendance_actionsAreMutuallyExclusive() {
        AttendancePage attendance = openAttendance();
        boolean both = attendance.isCheckInAvailable() && attendance.isCheckOutAvailable();
        Assert.assertFalse(both, "Check-In and Check-Out must not both be actionable simultaneously");
    }
}

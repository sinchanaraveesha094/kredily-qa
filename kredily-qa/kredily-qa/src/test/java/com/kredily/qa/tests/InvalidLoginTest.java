package com.kredily.qa.tests;

import com.kredily.qa.base.BaseTest;
import com.kredily.qa.pages.LoginPage;
import com.kredily.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/** Journey 2: invalid login - a data-driven set of hostile / sloppy inputs. */
public class InvalidLoginTest extends BaseTest {

    @DataProvider(name = "badCredentials")
    public Object[][] badCredentials() {
        String goodEmail = ConfigReader.get("valid.email");
        String goodPass = ConfigReader.get("valid.password");
        return new Object[][] {
                {"wrong password",         goodEmail,                    "WrongPass@123"},
                {"unregistered email",     "nobody.exists@yopmail.com",  goodPass},
                {"empty email",            "",                           goodPass},
                {"empty password",         goodEmail,                    ""},
                {"both empty",             "",                           ""},
                {"SQL injection in email", "' OR '1'='1' --",            goodPass},
                {"emoji in password",      goodEmail,                    "Pass@9865\uD83D\uDE00"},
                {"password wrong case",    goodEmail,                    goodPass.toLowerCase()},
                {"very long email",        "a".repeat(300) + "@yopmail.com", goodPass},
        };
    }

    @Test(dataProvider = "badCredentials",
          description = "J2 - Invalid credentials are rejected with a message and no dashboard access")
    public void invalidLogin_isRejected(String scenario, String email, String password) {
        LoginPage login = new LoginPage(driver);
        Assert.assertTrue(login.isDisplayed(), "Login screen should be visible");

        if (!email.isEmpty()) login.enterEmail(email);
        if (!password.isEmpty()) login.enterPassword(password);
        login.tapLogin();

        Assert.assertTrue(login.isErrorShown(), "[" + scenario + "] an error message should be shown");
        Assert.assertTrue(login.isDisplayed(), "[" + scenario + "] user must remain on the login screen");
    }
}

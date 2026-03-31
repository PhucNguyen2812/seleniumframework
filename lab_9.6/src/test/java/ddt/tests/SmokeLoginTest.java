package ddt.tests;

import ddt.base.BaseTest;
import ddt.config.ConfigReader;
import ddt.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeLoginTest extends BaseTest {

    @Test(description = "Smoke: valid login should reach inventory page")
    public void smokeValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver())
                .open(ConfigReader.get("base.url"))
                .login("standard_user", "secret_sauce");

        Assert.assertTrue(loginPage.isLoginSuccess(), "Valid login should be successful");
    }

    @Test(description = "Smoke: invalid login should show error")
    public void smokeInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver())
                .open(ConfigReader.get("base.url"))
                .login("standard_user", "wrongpass");

        Assert.assertFalse(loginPage.isLoginSuccess(), "Invalid login should not be successful");
        Assert.assertFalse(loginPage.getErrorMessage().isEmpty(), "Error message should be displayed");
    }
}

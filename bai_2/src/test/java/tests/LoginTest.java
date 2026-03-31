package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final ConfigReader config = ConfigReader.getInstance();

    @Test
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(getDriver()).open(config.getBaseUrl());
        InventoryPage inventoryPage = loginPage.login(config.get("valid.username"), config.get("valid.password"));

        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory should be loaded after valid login");
    }

    @Test
    public void testLoginFail_InvalidPassword() {
        LoginPage loginPage = new LoginPage(getDriver()).open(config.getBaseUrl());
        LoginPage currentPage = loginPage.loginExpectingFailure(config.get("valid.username"),
                config.get("invalid.password"));

        Assert.assertTrue(currentPage.isErrorDisplayed(), "Error should be displayed for wrong password");
        Assert.assertFalse(currentPage.getErrorMessage().isBlank(), "Error message should not be blank");
    }

    @Test
    public void testLoginFail_EmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver()).open(config.getBaseUrl());
        LoginPage currentPage = loginPage.loginExpectingFailure(config.get("empty.username"),
                config.get("valid.password"));

        Assert.assertTrue(currentPage.isErrorDisplayed(), "Error should be displayed for empty username");
        Assert.assertFalse(currentPage.getErrorMessage().isBlank(), "Error message should not be blank");
    }
}

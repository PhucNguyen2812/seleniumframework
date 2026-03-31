package tests;

import ddt.providers.JsonUserDataProvider;
import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest {

    @Test(dataProvider = "jsonUsers", dataProviderClass = JsonUserDataProvider.class)
    public void testLoginFromJson(String username, String password, boolean expectSuccess, String description) {
        LoginPage loginPage = new LoginPage(getDriver()).open(ConfigReader.getInstance().getBaseUrl());

        if (expectSuccess) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            Assert.assertTrue(inventoryPage.isLoaded(), "Login should succeed: " + description);
            return;
        }

        LoginPage currentPage = loginPage.loginExpectingFailure(username, password);
        Assert.assertTrue(currentPage.isErrorDisplayed(), "Error should be shown: " + description);
    }
}

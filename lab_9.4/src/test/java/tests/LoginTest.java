package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginSuccess_ShouldLoadInventoryAndUpdateBadge() {
        LoginPage loginPage = new LoginPage(getDriver())
                .open(ConfigReader.get("base.url"));

        InventoryPage inventoryPage = loginPage.login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password"));

        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be visible after valid login");

        CartPage cartPage = inventoryPage
                .addFirstItemToCart()
                .goToCart();

        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should contain 1 item after adding first product");
    }

    @Test
    public void loginFailure_ShouldDisplayErrorMessage() {
        LoginPage loginPage = new LoginPage(getDriver())
                .open(ConfigReader.get("base.url"));

        LoginPage currentLoginPage = loginPage.loginExpectingFailure(
                ConfigReader.get("invalid.username"),
                ConfigReader.get("invalid.password"));

        Assert.assertTrue(currentLoginPage.isErrorDisplayed(), "Error message should be displayed for invalid login");
        String errorText = currentLoginPage.getErrorMessage().toLowerCase();
        Assert.assertTrue(
                errorText.contains("epic sadface") || errorText.contains("do not match")
                        || errorText.contains("locked out"),
                "Error text should indicate login failure");
    }
}

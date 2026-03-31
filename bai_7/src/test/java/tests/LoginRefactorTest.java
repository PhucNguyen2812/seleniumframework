package tests;

import data.LoginDataProvider;
import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginRefactorTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void loginFlow_WithPOM_AndDDT(String username, String password, boolean shouldLoginSucceed) {
        LoginPage loginPage = new LoginPage(getDriver()).open(ConfigReader.get("base.url"));

        if (shouldLoginSucceed) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded after successful login");
            Assert.assertEquals(inventoryPage.getTitleText(), "Products");

            CartPage cartPage = inventoryPage
                    .addFirstItemToCart()
                    .goToCart();
            Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should contain exactly one item");
        } else {
            loginPage.loginExpectingFailure(username, password);
            Assert.assertTrue(
                    loginPage.getErrorMessage().contains("Username and password do not match"),
                    "Error message should be shown for invalid credentials");
        }
    }
}

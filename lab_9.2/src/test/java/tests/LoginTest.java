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
    public void loginSuccessAndAddItemToCart() {
        String baseUrl = ConfigReader.get("base.url");
        String username = ConfigReader.get("valid.username");
        String password = ConfigReader.get("valid.password");

        LoginPage loginPage = new LoginPage(getDriver()).open(baseUrl);
        InventoryPage inventoryPage = loginPage.login(username, password);

        // Assertion is in test class, not in page object.
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded after login");

        CartPage cartPage = inventoryPage
                .addFirstItemToCart()
                .goToCart();

        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should contain exactly 1 item");
    }
}

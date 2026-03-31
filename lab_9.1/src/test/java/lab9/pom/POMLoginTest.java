package lab9.pom;

import lab9.framework.base.BaseTest;
import lab9.framework.config.ConfigReader;
import lab9.pom.data.LoginDataProvider;
import lab9.pom.pages.CartPage;
import lab9.pom.pages.InventoryPage;
import lab9.pom.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POMLoginTest extends BaseTest {

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

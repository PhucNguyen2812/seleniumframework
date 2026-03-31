package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private final ConfigReader config = ConfigReader.getInstance();

    private InventoryPage loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(getDriver()).open(config.getBaseUrl());
        return loginPage.login(config.get("valid.username"), config.get("valid.password"));
    }

    @Test
    public void testAddItemToCart() {
        CartPage cartPage = loginAsStandardUser()
                .addFirstItemToCart()
                .goToCart();

        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should contain one item");
        List<String> names = cartPage.getItemNames();
        Assert.assertFalse(names.isEmpty(), "Cart item names should not be empty");
    }

    @Test
    public void testRemoveItemFromCart() {
        CartPage cartPage = loginAsStandardUser()
                .addFirstItemToCart()
                .goToCart()
                .removeFirstItem();

        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart should be empty after removing the only item");
    }

    @Test
    public void testCartEmpty() {
        CartPage cartPage = loginAsStandardUser().goToCart();

        Assert.assertEquals(cartPage.getItemCount(), 0, "Fresh cart should be empty");
    }
}

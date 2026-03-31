package tests;

import ddt.factory.TestDataFactory;
import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.CheckoutPage;
import framework.pages.LoginPage;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutWithRandomData() {
        Map<String, String> data = TestDataFactory.randomCheckoutData();

        System.out.println("FirstName: " + data.get("firstName"));
        System.out.println("LastName: " + data.get("lastName"));
        System.out.println("PostalCode: " + data.get("postalCode"));
        System.out.println("Email: " + data.get("email"));

        CheckoutPage checkoutPage = new LoginPage(getDriver())
                .open(ConfigReader.getInstance().getBaseUrl())
                .login(ConfigReader.getInstance().getValidUsername(), ConfigReader.getInstance().getValidPassword())
                .addFirstItemToCart()
                .goToCart()
                .goToCheckout()
                .fillCheckoutData(data)
                .continueCheckout();

        Assert.assertNotNull(checkoutPage, "Checkout flow should return a page object");
    }
}

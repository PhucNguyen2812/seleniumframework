package tests;

import ddt.providers.ExcelLoginDataProvider;
import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginDDTTest extends BaseTest implements ITest {

    private final ThreadLocal<String> testName = new ThreadLocal<>();

    @Override
    public String getTestName() {
        String name = testName.get();
        return name == null ? "ExcelLoginCase" : name;
    }

    @Test(dataProvider = "excelLoginData", dataProviderClass = ExcelLoginDataProvider.class, groups = { "smoke",
            "regression" })
    public void testLoginFromExcel(String username, String password, String expected, String description) {
        testName.set(description);
        Reporter.getCurrentTestResult().setAttribute("description", description);

        LoginPage loginPage = new LoginPage(getDriver()).open(ConfigReader.getInstance().getBaseUrl());

        if (expected.startsWith("http")) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            Assert.assertEquals(inventoryPage.getCurrentUrl(), expected, "URL mismatch: " + description);
            Assert.assertTrue(inventoryPage.isLoaded(), "Inventory should be loaded: " + description);
            return;
        }

        LoginPage currentPage = loginPage.loginExpectingFailure(username, password);
        Assert.assertTrue(currentPage.isErrorDisplayed(), "Error should be displayed: " + description);
        Assert.assertTrue(
                currentPage.getErrorMessage().toLowerCase().contains(expected.toLowerCase()),
                "Error mismatch: " + description);
    }
}

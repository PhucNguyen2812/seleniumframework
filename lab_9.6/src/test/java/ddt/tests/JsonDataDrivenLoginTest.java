package ddt.tests;

import ddt.base.BaseTest;
import ddt.config.ConfigReader;
import ddt.pages.LoginPage;
import ddt.providers.LoginDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonDataDrivenLoginTest extends BaseTest {

    @Test(dataProvider = "jsonLoginData", dataProviderClass = LoginDataProviders.class)
    public void loginWithJsonData(String username, String password, boolean expectSuccess, String role) {
        LoginPage loginPage = new LoginPage(getDriver())
                .open(ConfigReader.get("base.url"))
                .login(username, password);

        boolean actualSuccess = loginPage.isLoginSuccess();
        Assert.assertEquals(actualSuccess, expectSuccess, "JSON case failed for role: " + role);
    }
}

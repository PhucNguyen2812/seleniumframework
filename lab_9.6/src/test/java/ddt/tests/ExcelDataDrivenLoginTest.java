package ddt.tests;

import ddt.base.BaseTest;
import ddt.config.ConfigReader;
import ddt.pages.LoginPage;
import ddt.providers.LoginDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExcelDataDrivenLoginTest extends BaseTest {

    @Test(dataProvider = "excelLoginData", dataProviderClass = LoginDataProviders.class)
    public void loginWithExcelData(String username, String password, String expectedResult, String description) {
        LoginPage loginPage = new LoginPage(getDriver())
                .open(ConfigReader.get("base.url"))
                .login(username, password);

        boolean actualSuccess = loginPage.isLoginSuccess();
        boolean expectedSuccess = "SUCCESS".equalsIgnoreCase(expectedResult);

        Assert.assertEquals(actualSuccess, expectedSuccess, "Excel case failed: " + description);
    }
}

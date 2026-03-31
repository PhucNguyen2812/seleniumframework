package lab9.pom.data;

import lab9.framework.config.ConfigReader;
import org.testng.annotations.DataProvider;

public final class LoginDataProvider {

    private LoginDataProvider() {
    }

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][] {
                { ConfigReader.get("valid.username"), ConfigReader.get("valid.password"), true },
                { ConfigReader.get("invalid.username"), ConfigReader.get("invalid.password"), false }
        };
    }
}

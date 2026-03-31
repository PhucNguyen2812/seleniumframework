package ddt.providers;

import ddt.model.UserData;
import ddt.utils.ExcelReader;
import ddt.utils.ExcelTestDataBuilder;
import ddt.utils.JsonReader;
import java.io.IOException;
import java.util.List;
import org.testng.annotations.DataProvider;

public final class LoginDataProviders {

    private LoginDataProviders() {
    }

    @DataProvider(name = "loginData", parallel = true)
    public static Object[][] loginData() {
        return new Object[][] {
                { "standard_user", "secret_sauce", "SUCCESS", "valid_user_login" },
                { "locked_out_user", "secret_sauce", "FAIL", "locked_user_cannot_login" },
                { "standard_user", "wrongpass", "FAIL", "wrong_password" },
                { "", "secret_sauce", "FAIL", "empty_username" }
        };
    }

    @DataProvider(name = "excelLoginData", parallel = true)
    public static Object[][] excelLoginData() {
        String path = "target/testdata/login_data.xlsx";
        ExcelTestDataBuilder.ensureFile(path);
        return ExcelReader.getData(path, "LoginCases");
    }

    @DataProvider(name = "jsonLoginData", parallel = true)
    public static Object[][] jsonLoginData() {
        try {
            List<UserData> users = JsonReader.readUsers("src/test/resources/testdata/users.json");
            return users.stream()
                    .map(u -> new Object[] { u.getUsername(), u.getPassword(), u.isExpectSuccess(), u.getRole() })
                    .toArray(Object[][]::new);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read JSON test data", e);
        }
    }
}

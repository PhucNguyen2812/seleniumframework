package ddt.providers;

import ddt.model.UserData;
import ddt.utils.JsonReader;
import java.util.List;
import org.testng.annotations.DataProvider;

public final class JsonUserDataProvider {

    private JsonUserDataProvider() {
    }

    @DataProvider(name = "jsonUsers")
    public static Object[][] jsonUsers() {
        List<UserData> users = JsonReader.readUsers("src/test/resources/testdata/users.json");
        return users.stream()
                .map(u -> new Object[] { u.getUsername(), u.getPassword(), u.isExpectSuccess(), u.getDescription() })
                .toArray(Object[][]::new);
    }
}

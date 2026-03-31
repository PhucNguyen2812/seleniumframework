package ddt.factory;

import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class TestDataFactory {

    private static final Faker FAKER = new Faker(new Locale("vi"));

    private TestDataFactory() {
    }

    public static String randomFirstName() {
        return FAKER.name().firstName();
    }

    public static String randomLastName() {
        return FAKER.name().lastName();
    }

    public static String randomPostalCode() {
        return FAKER.address().zipCode();
    }

    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static Map<String, String> randomCheckoutData() {
        Map<String, String> data = new HashMap<>();
        data.put("firstName", randomFirstName());
        data.put("lastName", randomLastName());
        data.put("postalCode", randomPostalCode());
        data.put("email", randomEmail());
        return data;
    }
}

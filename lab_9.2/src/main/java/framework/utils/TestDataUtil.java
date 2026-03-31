package framework.utils;

public final class TestDataUtil {

    private TestDataUtil() {
    }

    // Basic utility for unique values when needed in future tests.
    public static String uniqueSuffix() {
        return String.valueOf(System.currentTimeMillis());
    }
}

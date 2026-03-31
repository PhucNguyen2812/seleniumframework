package framework.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {

    private static final DateTimeFormatter TS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtil() {
    }

    public static String capture(WebDriver driver, String testName) {
        try {
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);

            String fileName = sanitize(testName) + "_" + LocalDateTime.now().format(TS_FORMAT) + ".png";
            Path filePath = dir.resolve(fileName);

            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(filePath, bytes);
            return filePath.toString();
        } catch (IOException e) {
            return "screenshot_capture_failed: " + e.getMessage();
        }
    }

    private static String sanitize(String input) {
        return input == null ? "unknown_test" : input.replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}

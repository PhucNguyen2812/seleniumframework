package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import framework.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Returns driver of current thread to make parallel execution safe.
    protected WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters({ "browser", "env" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional("dev") String env) {
        // Keep selected environment available for config resolution.
        System.setProperty("env", env);

        WebDriver driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(ConfigReader.getInstance().getBaseUrl());

        tlDriver.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriver driver = getDriver();
        if (driver == null) {
            return;
        }

        // Capture evidence when test fails to speed up debugging.
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.capture(driver, result.getName());
            Allure.addAttachment("Screenshot path", screenshotPath);
            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(ScreenshotUtil.captureAsBytes(driver)),
                    ".png");
        }

        driver.quit();
        // Critical cleanup for parallel execution to avoid memory leak.
        tlDriver.remove();
    }
}

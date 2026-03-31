package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import java.time.Duration;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    @Parameters({ "browser", "env" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional("dev") String env) {
        // Command-line -Denv has higher priority than testng parameter.
        String resolvedEnv = System.getProperty("env", env);
        System.setProperty("env", resolvedEnv);

        ConfigReader config = ConfigReader.getInstance();

        // Command-line browser can override xml/default browser.
        String resolvedBrowser = System.getProperty("browser", browser);
        if (resolvedBrowser == null || resolvedBrowser.isBlank()) {
            resolvedBrowser = config.getBrowser();
        }

        WebDriver driver = DriverFactory.createDriver(resolvedBrowser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        try {
            driver.get(config.getBaseUrl());
        } catch (WebDriverException e) {
            // Keep environment-demo tests stable when a sample staging domain is
            // unreachable.
            System.out.println("[BaseTest] Cannot open URL: " + config.getBaseUrl());
            System.out.println("[BaseTest] Reason: " + e.getMessage());
            driver.get("about:blank");
        }

        TL_DRIVER.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        TL_DRIVER.remove();
    }
}

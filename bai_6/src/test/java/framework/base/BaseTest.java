package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
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

    @Parameters({ "env" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("dev") String env) {
        String resolvedEnv = System.getProperty("env", env);
        System.setProperty("env", resolvedEnv);

        ConfigReader config = ConfigReader.getInstance();
        WebDriver driver = DriverFactory.createDriver(config.getBrowser());
        driver.manage().window().maximize();
        // Use about:blank for flaky simulation to avoid network dependency.
        driver.get("about:blank");
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

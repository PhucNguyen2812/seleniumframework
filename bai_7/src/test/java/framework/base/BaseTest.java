package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver(ConfigReader.get("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        TL_DRIVER.set(driver);
    }

    protected WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = TL_DRIVER.get();
        if (driver != null) {
            driver.quit();
        }
        TL_DRIVER.remove();
    }
}

package lab9.naive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NaiveLoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Selenium 4.6+ can auto-manage browser driver binaries.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginSuccess_NaiveStyle() {
        // Hardcoded test data (anti-pattern) for demo purpose.
        String url = "https://www.saucedemo.com/";
        String username = "standard_user";
        String password = "secret_sauce";

        driver.get(url);

        // Directly locating and interacting in test method (anti-pattern).
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        WebElement title = driver.findElement(By.className("title"));
        Assert.assertEquals(title.getText(), "Products", "Login should open inventory page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package lab9.pom.pages;

import lab9.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators are centralized in the page object.
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(String url) {
        driver.get(url);
        return this;
    }

    public InventoryPage login(String username, String password) {
        waitAndType(usernameInput, username);
        waitAndType(passwordInput, password);
        waitAndClick(loginButton);
        return new InventoryPage(driver);
    }

    public LoginPage loginExpectingFailure(String username, String password) {
        waitAndType(usernameInput, username);
        waitAndType(passwordInput, password);
        waitAndClick(loginButton);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}

package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// This class intentionally calls all BasePage methods so removing one breaks compile.
public class BasePageDependencyProbe extends BasePage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public BasePageDependencyProbe(WebDriver driver) {
        super(driver);
    }

    public void useAllBaseMethods() {
        waitForPageLoad();
        scrollToElement(username);
        waitAndType(username, "demo");
        waitAndType(password, "demo");
        waitAndClick(loginButton);
        getText(loginButton);
        getAttribute(loginButton, "id");
        isElementVisible(By.id("login-button"));
    }
}

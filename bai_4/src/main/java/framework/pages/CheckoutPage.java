package framework.pages;

import framework.base.BasePage;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillCheckoutData(Map<String, String> data) {
        waitAndType(firstNameInput, data.get("firstName"));
        waitAndType(lastNameInput, data.get("lastName"));
        waitAndType(postalCodeInput, data.get("postalCode"));
        return this;
    }

    public CheckoutPage continueCheckout() {
        waitAndClick(continueButton);
        return this;
    }
}

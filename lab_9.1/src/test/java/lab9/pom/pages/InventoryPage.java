package lab9.pom.pages;

import lab9.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By firstAddToCartButton = By.cssSelector(".inventory_item:first-of-type button.btn_inventory");
    private final By cartLink = By.cssSelector("a.shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return "Products".equals(getText(pageTitle));
    }

    public String getTitleText() {
        return getText(pageTitle);
    }

    public InventoryPage addFirstItemToCart() {
        waitAndClick(firstAddToCartButton);
        return this;
    }

    public CartPage goToCart() {
        waitAndClick(cartLink);
        return new CartPage(driver);
    }
}

package framework.pages;

import framework.base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> itemNameLabels;

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isElementVisible(By.cssSelector(".inventory_list"));
    }

    public InventoryPage addFirstItemToCart() {
        if (!addToCartButtons.isEmpty()) {
            WebElement firstButton = addToCartButtons.get(0);
            scrollToElement(firstButton);
            waitAndClick(firstButton);
        }
        return this;
    }

    public InventoryPage addItemByName(String name) {
        for (int i = 0; i < itemNameLabels.size() && i < addToCartButtons.size(); i++) {
            String itemName = getText(itemNameLabels.get(i));
            if (itemName.equalsIgnoreCase(name)) {
                WebElement button = addToCartButtons.get(i);
                scrollToElement(button);
                waitAndClick(button);
                break;
            }
        }
        return this;
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException | NumberFormatException e) {
            return 0;
        }
    }

    public CartPage goToCart() {
        waitAndClick(cartLink);
        return new CartPage(driver);
    }
}

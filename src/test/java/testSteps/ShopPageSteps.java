package testSteps;

import constants.Defaults;
import constants.InventoryItems;
import library.WebLibrary;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;
import utilities.loggerutilities.LogUtility;

import java.util.Map;

/**
 * author Love
 */

public class ShopPageSteps {
    private final By cartButton = By.id("nav-cart");
    private final By itemBuy = By.xpath("//h4[text()='" + Defaults.GENERIC_LOCATOR_REGEX + "']/../p/a");
    private final WebLibrary webLibrary;

    public ShopPageSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public void addItemsToCart(Map<InventoryItems, Integer> items) {
        LogUtility.info("Items to purchase: " + items);
        items.forEach((key, value) -> webLibrary.multipleClicks(webLibrary.generateLocator(itemBuy, key.getItemName()), value));
    }

    public void clickCartButton() {
        webLibrary.click(cartButton);
    }
}

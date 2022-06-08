package testSteps;

import constants.Defaults;
import constants.InventoryItems;
import library.WebLibrary;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;
import utilities.commonutilities.JavaUtility;
import utilities.loggerutilities.LogUtility;

import java.util.HashMap;
import java.util.Map;

/**
 * author Love
 */

public class ShopPageSteps {
    private final By cartButton = By.id("nav-cart");
    private final By itemBuy = By.xpath("//h4[text()='" + Defaults.GENERIC_LOCATOR_REGEX + "']/../p/a");
    private final By itemPrice = By.xpath("//h4[text()='" + Defaults.GENERIC_LOCATOR_REGEX + "']/../p/span");
    private final WebLibrary webLibrary;

    public ShopPageSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public Map<InventoryItems, Double> getItemPrice(Map<InventoryItems, Integer> itemList) {
        Map<InventoryItems, Double> itempriceMap = new HashMap<>();
        itemList.forEach((item, count) -> itempriceMap.put(item, JavaUtility.convertStringToDouble(webLibrary.getText(webLibrary.generateLocator(itemPrice, item.getItemName())).replace("$", ""))));
        return itempriceMap;
    }

    public Map<InventoryItems, Double> getExpectedSubTotalAmount(Map<InventoryItems, Integer> itemWithCount, Map<InventoryItems, Double> itemWithPrice) {
        Map<InventoryItems, Double> subTotalMap = new HashMap<>();
        itemWithCount.forEach((item, count) -> subTotalMap.put(item, itemWithPrice.get(item) * count));
        return subTotalMap;
    }

    public void addItemsToCart(Map<InventoryItems, Integer> items) {
        LogUtility.info("Items to purchase: " + items);
        items.forEach((key, value) -> webLibrary.multipleClicks(webLibrary.generateLocator(itemBuy, key.getItemName()), value));
    }

    public void clickCartButton() {
        webLibrary.click(cartButton);
    }
}

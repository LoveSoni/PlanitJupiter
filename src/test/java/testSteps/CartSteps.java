package testSteps;

import constants.Defaults;
import constants.InventoryItems;
import library.WebLibrary;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;
import utilities.commonutilities.JavaUtility;

import java.util.HashMap;
import java.util.Map;

/**
 * author Love
 */
public class CartSteps {
    private final By itemQuantity = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/parent::tr/td/input");
    private final By itemPrice = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/following-sibling::td[1]");
    private final WebLibrary webLibrary;

    public CartSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public int getItemQuantity(String itemName) {
        return JavaUtility.convertStringToInteger(webLibrary.getAttribute(webLibrary.generateLocator(itemQuantity, itemName), Defaults.VALUE_KEY));
    }

    public Map<InventoryItems, Double> getItemPrice(Map<InventoryItems, Integer> itemList) {
        Map<InventoryItems, Double> priceMap = new HashMap<>();
        itemList.forEach((item, count) -> priceMap.put(item, JavaUtility.convertStringToDouble(webLibrary.getText(webLibrary.generateLocator(itemPrice, item.getItemName())).replace("$", ""))));
        return priceMap;
    }

    public Map<InventoryItems, Integer> getItemSubTotal(Map<InventoryItems, Integer> itemList) {
        Map<InventoryItems, Integer> subTotalMap = new HashMap<>();
        return subTotalMap;
    }
}

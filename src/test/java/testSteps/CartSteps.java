package testSteps;

import constants.Defaults;
import constants.InventoryItems;
import constants.ItemAmountType;
import library.WebLibrary;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;
import utilities.commonutilities.JavaUtility;
import utilities.loggerutilities.LogUtility;

import java.util.HashMap;
import java.util.Map;

/**
 * author Love
 */
public class CartSteps {
    private final By itemQuantity = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/parent::tr/td/input");
    private final By itemPrice = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/following-sibling::td[1]");
    private final By itemSubTotal = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/following-sibling::td[3]");
    private final By totalPrice = By.xpath("//strong[contains(@class,'total')]");

    private final WebLibrary webLibrary;

    public CartSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public int getItemQuantity(String itemName) {
        return JavaUtility.convertStringToInteger(webLibrary.getAttribute(webLibrary.generateLocator(itemQuantity, itemName), Defaults.VALUE_KEY));
    }

    public Map<InventoryItems, Double> getItemPriceOrSubtotal(Map<InventoryItems, Integer> itemList, ItemAmountType itemAmountType) {
        Map<InventoryItems, Double> priceMap = new HashMap<>();
        switch (itemAmountType) {
            case PRICE:
                itemList.forEach((item, count) -> priceMap.put(item, JavaUtility.convertStringToDouble(webLibrary.getText(webLibrary.generateLocator(itemPrice, item.getItemName())).replace("$", ""))));
                break;
            case SUBTOTAL:
                itemList.forEach((item, count) -> priceMap.put(item, JavaUtility.convertStringToDouble(webLibrary.getText(webLibrary.generateLocator(itemSubTotal, item.getItemName())).replace("$", ""))));
                break;
            default:
                LogUtility.error("Invalid amount type");
        }
        return priceMap;
    }

    public Double getTotalPrice() {
        return JavaUtility.convertStringToDouble(webLibrary.getText(totalPrice).replace("Total: ", "").trim());
    }
}

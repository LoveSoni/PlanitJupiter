package testSteps;

import constants.Defaults;
import constants.InventoryItems;
import constants.ItemAmountType;
import library.WebLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sessions.uisessions.SessionManager;
import utilities.commonutilities.JavaUtility;
import utilities.loggerutilities.LogUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author Love
 */
public class CartSteps {
    private final By itemQuantity = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/parent::tr/td/input");
    private final By itemHeadersName = By.xpath("//tr/th");
    private final By itemPriceSubTotal = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/../td["+ Defaults.INDEX_LOCATOR_REGEX+"]");
    private final By totalPrice = By.xpath("//strong[contains(@class,'total')]");

    private final WebLibrary webLibrary;

    public CartSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public int getItemQuantity(String itemName) {
        return JavaUtility.convertStringToInteger(webLibrary.getAttribute(webLibrary.generateLocatorByReplacingString(itemQuantity, itemName), Defaults.VALUE_KEY));
    }

    public List<WebElement> getItemHeaderName() {
        return webLibrary.getWebElements(itemHeadersName);
    }

    public int getColumnNumberOfItemHeaderTable(String columnName) {
        int indexNumber = 0;
        List<WebElement> itemHeaderList = getItemHeaderName();
        for (int i = 0; i < itemHeaderList.size(); i++) {
            if (itemHeaderList.get(i).getText().equalsIgnoreCase(columnName)) {
                indexNumber = i + 1;
                break;
            }
        }
        return indexNumber;
    }

    public Map<InventoryItems, Double> getItemPriceOrSubtotal(Map<InventoryItems, Integer> itemList, ItemAmountType itemAmountType) {
        Map<InventoryItems, Double> priceMap = new HashMap<>();
        switch (itemAmountType) {
            case PRICE:
                itemList.forEach((item, count) -> priceMap.put(item, JavaUtility.convertStringToDouble(webLibrary.getText(webLibrary.generateLocatorByReplacingId(webLibrary.generateLocatorByReplacingString(itemPriceSubTotal, item.getItemName()),getColumnNumberOfItemHeaderTable("Price"))).replace("$", ""))));
                break;
            case SUBTOTAL:
                itemList.forEach((item, count) -> priceMap.put(item, JavaUtility.convertStringToDouble(webLibrary.getText(webLibrary.generateLocatorByReplacingId(webLibrary.generateLocatorByReplacingString(itemPriceSubTotal, item.getItemName()),getColumnNumberOfItemHeaderTable("Subtotal"))).replace("$", ""))));
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

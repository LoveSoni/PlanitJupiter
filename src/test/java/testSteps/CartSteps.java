package testSteps;

import constants.Defaults;
import library.WebLibrary;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;
import utilities.commonutilities.JavaUtility;

/**
 * author Love
 */
public class CartSteps {
    private final By itemQuantity = By.xpath("//td[contains(text(),'" + Defaults.GENERIC_LOCATOR_REGEX + "')]/parent::tr/td/input");
    private final WebLibrary webLibrary;

    public CartSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public int getItemQuantity(String itemName) {
        return JavaUtility.convertStringToInteger(webLibrary.getAttribute(webLibrary.generateLocator(itemQuantity, itemName), Defaults.VALUE_KEY));
    }
}

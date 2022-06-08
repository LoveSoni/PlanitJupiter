package testSteps;

import library.WebLibrary;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;
import sessions.uisessions.WebSession;

/**
 * author Love
 */

public class ShopPageSteps {
    private final By cartButton = By.id("nav-cart");
    private final WebLibrary webLibrary;

    public ShopPageSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public void clickCartButton() {
        webLibrary.click(cartButton);
    }
}

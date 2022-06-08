package testSteps;

import library.WebLibrary;
import org.openqa.selenium.By;
import sessions.uisessions.SessionManager;

/**
 * author Love
 */
public class HomePageSteps {
    private final By contactTab = By.id("nav-contact");
    private final By shopTab = By.id("nav-shop");
    private final WebLibrary webLibrary;

    public HomePageSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public void navigateToContactPage() {
        webLibrary.click(contactTab);
    }

    public void navigateToShopTab() {
        webLibrary.click(shopTab);
    }

}

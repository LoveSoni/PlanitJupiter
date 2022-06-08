package sessions.uisessions;

/**
 * author Love
 */

import org.openqa.selenium.WebDriver;

public interface SessionManager {
    void initiateDriver();

    void quitDriver();

    void startSession();

    WebDriver getDriver();
}

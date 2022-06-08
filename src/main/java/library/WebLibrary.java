package library;

/**
 * author Love
 */

import constants.Defaults;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sessions.uisessions.SessionManager;
import utilities.loggerutilities.LogUtility;

import java.time.Duration;

public class WebLibrary {
    private WebDriver driver;

    public WebLibrary(SessionManager webSession) {
        driver = webSession.getDriver();
    }

    public void click(By locator) {
        isElementPresent(locator);
        LogUtility.info("Clicked on " + locator);
        try {
            getWebElement(locator).click();
        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            scrollToElement(locator);
            getWebElement(locator).click();
        }
    }

    public String getText(By locator) {
        isElementPresent(locator);
        String text = getWebElement(locator).getText();
        LogUtility.info("Get Text - " + text);
        return text;
    }

    public void enterText(By locator, String text) {
        isElementPresent(locator);
        WebElement webElement = getWebElement(locator);
        webElement.clear();
        click(locator);
        LogUtility.info("Entering text - " + text);
        webElement.sendKeys(text);
    }

    public void scrollToElement(By locator) {
        LogUtility.info("Scrolling to find element " + locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getWebElement(locator));
    }

    public boolean isElementVisible(By locator) {
        boolean res = false;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            res = true;
        } catch (Exception timeoutException) {
            LogUtility.warn("Element - " + locator + " is not Visible");
        }
        return res;
    }

    public boolean isElementPresent(By locator) {
        boolean res = false;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(Defaults.ELEMENT_EXPLICIT_WAIT));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            res = true;
        } catch (TimeoutException timeoutException) {
            LogUtility.error("Element - " + locator + " is not Present in DOM");
        }
        return res;
    }

    public WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

}
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
import java.util.List;

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

    public void multipleClicks(By locator, int numberOfClick) {
        for (int i = 0; i < numberOfClick; i++) {
            click(locator);
        }
    }

    public String getText(By locator) {
        isElementPresent(locator);
        String text = getWebElement(locator).getText();
        LogUtility.info("Get Text - " + text);
        return text;
    }

    public String getAttribute(By locator, String attributeName) {
        isElementPresent(locator);
        String value = getWebElement(locator).getAttribute(attributeName);
        LogUtility.info("Get Text -" + value);
        return value;
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

    public By generateLocatorByReplacingId(By locator, int index) {
        String locatorString = locator.toString();
        String locatorStrategy = locatorString.substring(locatorString.indexOf(Defaults.FULL_STOP) + 1, locatorString.indexOf(Defaults.COLUNS)).toLowerCase();
        String updatedLocatorString = locatorString.substring(locatorString.indexOf(Defaults.COLUNS) + 1).trim().replace(Defaults.INDEX_LOCATOR_REGEX, String.valueOf(index));
        return generateLocator(locatorStrategy, updatedLocatorString);
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

    public List<WebElement> getWebElements(By locator) {
        isElementPresent(locator);
        return driver.findElements(locator);
    }

    public By generateLocatorByReplacingString(By locator, String updatedValue) {
        String locatorString = locator.toString();
        String locatorStrategy = locatorString.substring(locatorString.indexOf(Defaults.FULL_STOP) + 1, locatorString.indexOf(Defaults.COLUNS)).toLowerCase();
        String updatedLocatorString = locatorString.substring(locatorString.indexOf(Defaults.COLUNS) + 1).trim().replace(Defaults.GENERIC_LOCATOR_REGEX, updatedValue);
        return generateLocator(locatorStrategy, updatedLocatorString);
    }

    public By generateLocator(String locatorStrategy, String updatedLocatorString) {
        By element = null;
        // for now in our framework there are only two locators strategy used
        switch (locatorStrategy) {
            case "xpath":
                element = By.xpath(updatedLocatorString);
                break;
            case "id":
                element = By.id(updatedLocatorString);
                break;
            default:
                LogUtility.error("Locator strategy not defined");
        }
        return element;
    }

}

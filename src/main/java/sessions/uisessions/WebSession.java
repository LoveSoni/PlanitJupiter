package sessions.uisessions;

/**
 * author Love
 */

import constants.Urls;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import utilities.loggerutilities.LogUtility;
import utilities.readerutilities.EnvironmentReader;

public class WebSession implements SessionManager {
    private WebDriver driver;
    private final String browserName = EnvironmentReader.getBrowserName();
    private final boolean headlessMode = EnvironmentReader.getHeadlessMode();

    @Override
    public void initiateDriver() {
        setUpBrowser();
        maximizeBrowser();
    }

    public void setUpBrowser() {
        LogUtility.info("Running Test on " + browserName + " Browser");
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromiumdriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                LogUtility.error("Please check the browser name carefully");
        }
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(headlessMode);
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(headlessMode);
        return firefoxOptions;
    }

    public void maximizeBrowser() {
        this.driver.manage().window().maximize();
    }

    @Override
    public void startSession() {
        this.driver.get(Urls.JUPITER_APP_URL);
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public void quitDriver() {
        this.driver.quit();
    }


}

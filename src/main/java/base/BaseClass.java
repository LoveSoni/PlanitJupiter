package base;

/**
 * author Love
 */

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sessions.uisessions.SessionManager;
import sessions.uisessions.WebSession;
import utilities.readerutilities.EnvironmentReader;

public class BaseClass {
    protected SessionManager sessionManager;

    @BeforeMethod()
    public void setUpMethod(ITestContext iTestContext) {
        initializeSessionManager();
        sessionManager.initiateDriver();
        sessionManager.startSession();
        iTestContext.setAttribute("WebDriver", sessionManager.getDriver());
    }

    public void initializeSessionManager() {
        String platform = EnvironmentReader.getPlatformName();
        //Further we can extend for other platforms like Mobile/Api etc
        switch (platform.toLowerCase()) {
            case "web":
                sessionManager = new WebSession();
                break;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void baseTearDown() {
        sessionManager.quitDriver();
    }

}

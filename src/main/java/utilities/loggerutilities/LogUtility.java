package utilities.loggerutilities;

/**
 * author Love
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.reportingUtilities.ExtentManager;

import java.util.Optional;

public class LogUtility {
    private static final Logger logger = LogManager.getLogger(LogUtility.class);

    public static void info(String message) {
        logger.info(message);
        Optional.ofNullable(ExtentManager.getExtentTest()).ifPresent(extentTest -> extentTest.info(message));
    }

    public static void pass(String message) {
        logger.info(message);
        Optional.ofNullable(ExtentManager.getExtentTest()).ifPresent(extentTest -> extentTest.pass(message));
    }

    public static void warn(String message) {
        logger.warn(message);
        Optional.ofNullable(ExtentManager.getExtentTest()).ifPresent(extentTest -> extentTest.warning(message));
    }

    public static void error(String message) {
        logger.error(message);
        Optional.ofNullable(ExtentManager.getExtentTest()).ifPresent(extentTest -> extentTest.fail(message));
    }


}

package utilities.loggerutilities;

/**
 * author Love
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtility {
    private static final Logger logger = LogManager.getLogger(LogUtility.class);

    public static void  info(String message) {
        logger.info(message);
    }

    public static void pass(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }


}

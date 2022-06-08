package utilities.readerutilities;

/**
 * author love
 */

import constants.Defaults;
import utilities.commonutilities.JavaUtility;

import java.util.Optional;
import java.util.Properties;

public class EnvironmentReader {

    private static Properties defaultEnvironmentProperties = PropertyReader.readProperty(Defaults.DEFAULT_CONFIG_PROPERTIES_PATH);

    private static String environment = System.getProperty("environment");

    private static String browserName = System.getProperty("browserName");

    private static String headless = System.getProperty("headless");

    private static String platformName = System.getProperty("platformName");

    public static String getBrowserName() {
        return Optional.ofNullable(browserName).orElseGet(EnvironmentReader::getDefaultBrowserName);
    }

    public static String getDefaultBrowserName() {
        return defaultEnvironmentProperties.getProperty("browserName");
    }

    public static String getTestEnvironment() {
        return Optional.ofNullable(environment).orElseGet(EnvironmentReader::getDefaultEnv);
    }

    public static String getDefaultEnv() {
        return defaultEnvironmentProperties.getProperty("environment");
    }

    public static boolean getHeadlessMode() {
        return JavaUtility.convertStringToBoolean(Optional.ofNullable(headless).orElseGet(EnvironmentReader::getDefaultHeadlessMode));
    }

    public static String getDefaultHeadlessMode() {
        return defaultEnvironmentProperties.getProperty("headless");
    }

    public static String getPlatformName() {
        return Optional.ofNullable(platformName).orElseGet(EnvironmentReader::getDefaultPlatformName);
    }

    public static String getDefaultPlatformName() {
        return defaultEnvironmentProperties.getProperty("platformName");
    }
}

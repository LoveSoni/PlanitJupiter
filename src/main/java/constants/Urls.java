package constants;

/**
 * author Love
 */

import utilities.readerutilities.PropertyReader;

import java.util.Properties;

public class Urls {
    private static Properties environmentProperties = PropertyReader.readProperty(ProjectPath.ENVIRONMENT_CONFIG_PATH);

    public static String JUPITER_APP_URL = environmentProperties.getProperty(Defaults.APP_URL_KEY);
}

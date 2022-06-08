package constants;

/**
 * author Love
 */

import java.io.File;

public class Defaults {
    // we can keep our constants in this class
    private static final String slash = File.separator;

    public static String DEFAULT_CONFIG_PROPERTIES_PATH = System.getProperty("user.dir") + slash + "src" + slash + "main" + slash + "resources" + slash + "defaultConfig.properties";

    public static int ELEMENT_EXPLICIT_WAIT = 15;

    public static String SRC = "src";

    public static String MAIN = "main";

    public static String RESOURCES = "resources";

    public static String ENVIRONMENTS = "environments";

    public static String APP_URL_KEY = "appUrl";

    public static String TEST_FORENAME_KEY = "testForeName";

    public static String TEST_EMAIL_KEY = "testEmail";

    public static String TEST_MESSAGE_KEY = "testMessage";
}

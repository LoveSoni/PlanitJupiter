package constants;

/**
 * author Love
 */

import utilities.readerutilities.EnvironmentReader;

import java.io.File;

public class ProjectPath {

    public static String PROJECT_ROOT_PATH = System.getProperty("user.dir");

    public static String slash = File.separator;

    public static String SRC_DIRECTORY_PATH = PROJECT_ROOT_PATH + slash + Defaults.SRC;

    public static String MAIN_DIRECTORY_PATH = SRC_DIRECTORY_PATH + slash + Defaults.MAIN;

    public static String MAIN_RESOURCE_PATH = MAIN_DIRECTORY_PATH + slash + Defaults.RESOURCES;

    public static String MAIN_ENVIRONMENT_PATH = MAIN_RESOURCE_PATH + slash + Defaults.ENVIRONMENTS;

    public static String ENVIRONMENT_CONFIG_PATH = MAIN_ENVIRONMENT_PATH + slash + EnvironmentReader.getTestEnvironment() + ".properties";


}

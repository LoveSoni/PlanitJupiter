package utilities.readerutilities;

/**
 * author Love
 */

import utilities.loggerutilities.LogUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PropertyReader {
    private static FileInputStream fileInputStream;
    private static Properties properties;

    public static Properties readProperty(String filePath) {
        try {
            fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException f) {
            LogUtility.error(f.getMessage());
        }
        return properties;
    }

    public static Map<String, String> getAllKeysAndValues(String filePath) {
        Properties properties = readProperty(filePath);
        Map<String, String> map = new HashMap<>();
        properties.forEach((key, value) -> map.put(String.valueOf(key), String.valueOf(value)));
        return map;
    }

}

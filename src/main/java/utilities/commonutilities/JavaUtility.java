package utilities.commonutilities;

/**
 * author Love
 */
public class JavaUtility {
    public static boolean convertStringToBoolean(String text) {
        return Boolean.parseBoolean(text);
    }

    public static int convertStringToInteger(String text) {
        return Integer.parseInt(text);
    }

    public static double convertStringToDouble(String text) {
        return Double.parseDouble(text);
    }
}

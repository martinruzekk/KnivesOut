package util;

/**
 * A utility class for removing end comma from a string.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public final class RemoveEndComma {
    /**
     * Removes comma at the end of the string input.
     *
     * @param str the str
     * @return the string
     */
    public static String removeEndComma(String str) {
        str = str.trim();
        if (str.endsWith(",")) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }
}

package util;

/**
 * A utility class for removing end comma from a string.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public final class RemoveEndComma {

    private RemoveEndComma() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Removes comma at the end of the string input.
     *
     * @param str the str
     * @return the string
     */
    public static String removeEndComma(String str) {
        String strTrimmed = str.trim();
        if (strTrimmed.endsWith(",")) {
            return strTrimmed.substring(0, strTrimmed.length() - 1);
        }
        return strTrimmed;
    }
}

package io.metamorphic.commons.utils;

/**
 * Created by markmo on 11/07/2015.
 */
public class StringUtils {

    public static int countSubstring(String str, String sub) {
        if (str == null || sub == null || str.length() < sub.length()) return 0;
        int i = 0, j = 0;
        while (true) {
            int k = str.indexOf(sub, i);
            if (k == -1) return j;
            j += 1;
            i = k + sub.length();
        }
    }

    public static String zeroPad(long l, int len) {
        return String.format("%0" + len + "d", l);
    }

    public static boolean hasNoValue(String str) {
        if (str == null) return true;
        String trimmed = str.trim();
        return (trimmed.isEmpty() || trimmed.matches("0+"));
    }

    public static boolean hasValue(String str) {
        return !hasNoValue(str);
    }
}

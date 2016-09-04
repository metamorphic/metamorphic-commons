package io.metamorphic.commons.utils;

/**
 * Created by markmo on 11/07/2015.
 */
public class Humanize {

    public static String humanize(boolean value) {
        return humanize(value, "YES");
    }

    public static String humanize(boolean value, String format) {
        switch (format.toUpperCase()) {
            case "TRUE":
                return (value ? "TRUE" : "FALSE");
            case "ON":
                return (value ? "ON" : "OFF");
            case "1":
                return (value ? "1" : "0");
            default:
                return (value ? "YES" : "NO");
        }
    }
}

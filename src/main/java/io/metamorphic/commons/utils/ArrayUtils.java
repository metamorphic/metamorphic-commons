package io.metamorphic.commons.utils;

/**
 * Created by markmo on 11/07/2015.
 */
public class ArrayUtils {

    public static boolean memberOf(String[] array, String element) {
        for (String elem : array) {
            if (elem.equals(element)) {
                return true;
            }
        }
        return false;
    }
}

package com.rajtomar.healthandglow.interview.util;

/**
 * Utility class which perform some operations on java objects.
 *
 * @author Raj Tomar
 */
public class ObjectUtils {

    /**
     * Utility method to validate an object is null or not.
     *
     * @param object any java object.
     */
    public static void assertNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("null value is not allowed.");
        }
    }

}

/***/
package org.eclipse.ui.tests.harness.util;

import java.util.Random;

/**
* <code>ArrayUtil</code> contains methods for array
* examination.
*/
public class ArrayUtil {

    private static Random randomBox = new Random();

    /**
* Returns a random object chosen from an array.
*
* @param array the input array
* @return a random object in the array
*/
    public static Object pickRandom(Object[] array) {
        int num = randomBox.nextInt(array.length);
        return array[num];
    }

    /**
* Returns whether an array is not null and
* each object in the array is not null.
*
* @param array the input array
* @return <code>true or false</code>
*/
    public static boolean checkNotNull(Object[] array) {
        if (array == null)
            return false;
        for (int i = 0; i < array.length; i++) if (array[i] == null)
            return false;
        return true;
    }

    /**
* Returns whether an array contains a given object.
*
* @param array the input array
* @param element the test object
* @return <code>true</code> if the array contains the object,
* 		<code>false</code> otherwise.
*/
    public static boolean contains(Object[] array, Object element) {
        if (array == null || element == null)
            return false;
        for (int i = 0; i < array.length; i++) if (array[i] == element)
            return true;
        return false;
    }

    /**
* Returns whether two arrays are equal.  They must
* have the same size and the same contents.
*
* @param one the first array
* @param two the second array
* @return <code>true</code> if the array are equal,
* 		<code>false</code> otherwise.
*/
    public static boolean equals(Object[] one, Object[] two) {
        if (one.length != two.length)
            return false;
        for (int i = 0; i < one.length; i++) if (one[i] != two[i])
            return false;
        return true;
    }
}

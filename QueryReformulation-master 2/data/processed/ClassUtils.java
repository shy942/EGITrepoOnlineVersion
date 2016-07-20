/***/
package org.eclipse.e4.ui.css.core.utils;

/**
* Class utils.
*/
public class ClassUtils {

    /**
* Return the simple name of Class <code>c</code>.
*
* @param c
* @return
*/
    public static String getSimpleName(Class<?> c) {
        String name = c.getName();
        int index = name.lastIndexOf(".");
        if (index > 0) {
            name = name.substring(index + 1, name.length());
        }
        // inner classes contain a "$" as in Outer$Inner. As "$" is not allowed
        // as part of a CSS selector
        // we translate a "$" into a "-" here.
        name = name.replace('$', '-');
        return name;
    }

    /**
* Return the package name of Class <code>c</code>.
*
* @param c
* @return
*/
    public static String getPackageName(Class<?> c) {
        String name = c.getName();
        int index = name.lastIndexOf(".");
        if (index > 0) {
            return name.substring(0, index);
        }
        return null;
    }
}

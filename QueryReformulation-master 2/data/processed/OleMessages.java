/***/
package org.eclipse.ui.internal.editorsupport.win32;

import com.ibm.icu.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
* Utility class which helps managing messages
*/
public class OleMessages {

    //$NON-NLS-1$
    private static final String RESOURCE_BUNDLE = "org.eclipse.ui.internal.editorsupport.win32.messages";

    private static ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);

    private  OleMessages() {
    // prevent instantiation of class
    }

    /**
* Returns the formatted message for the given key in the resource bundle.
*
* @param key
*            the resource name
* @param args
*            the message arguments
* @return the string
*/
    public static String format(String key, Object[] args) {
        return MessageFormat.format(getString(key), args);
    }

    /**
* Returns the resource object with the given key in the resource bundle. If
* there isn't any value under the given key, the key is returned.
*
* @param key
*            the resource name
* @return the string
*/
    public static String getString(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
* Returns the resource object with the given key in the resource bundle. If
* there isn't any value under the given key, the default value is returned.
*
* @param key
*            the resource name
* @param def
*            the default value
* @return the string
*/
    public static String getString(String key, String def) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return def;
        }
    }
}

/***/
package org.eclipse.ui.views.properties;

/**
* Utility class which helps manage messages.
* @deprecated These messages are not API and should not be referenced
* outside of this plug-in.
*/
@Deprecated
class IDEPropertiesMessages {

    private  IDEPropertiesMessages() {
    // prevent instantiation of class
    }

    /**
* Returns the formatted message for the given key in
* the resource bundle.
*
* @param key the resource name
* @param args the message arguments
* @return the string
*/
    public static String format(String key, Object[] args) {
        return key;
    }

    /**
* Returns the resource object with the given key in
* the resource bundle. If there isn't any value under
* the given key, the key is returned.
*
* @param key the resource name
* @return the string
*/
    public static String getString(String key) {
        return key;
    }
}

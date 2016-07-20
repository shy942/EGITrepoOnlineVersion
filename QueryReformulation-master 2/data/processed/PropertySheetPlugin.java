/***/
package org.eclipse.ui.examples.propertysheet;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
* This is the top-level class of the property sheet example.
*
* @see AbstractUIPlugin for additional information on UI plugins
*/
public class PropertySheetPlugin extends AbstractUIPlugin {

    // Default instance of the receiver
    private static PropertySheetPlugin inst;

    /**
* Create the PropertySheet plugin and cache its default instance
*/
    public  PropertySheetPlugin() {
        if (inst == null)
            inst = this;
    }

    /**
* Returns the plugin singleton.
*
* @return the default PropertySheetPlugin instance
*/
    public static PropertySheetPlugin getDefault() {
        return inst;
    }
}

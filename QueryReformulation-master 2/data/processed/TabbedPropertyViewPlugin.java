/***/
package org.eclipse.ui.internal.views.properties.tabbed;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
* The common ui properties plug-in.
*
* @author Anthony Hunter
*/
public class TabbedPropertyViewPlugin extends AbstractUIPlugin {

    private static TabbedPropertyViewPlugin plugin;

    /**
* Constructor for TabbedPropertyViewPlugin.
*/
    public  TabbedPropertyViewPlugin() {
        super();
        plugin = this;
    }

    /**
* Retrieve the plug-in class for this plug-in.
* @return the plug-in class for this plug-in.
*/
    public static TabbedPropertyViewPlugin getPlugin() {
        return plugin;
    }
}

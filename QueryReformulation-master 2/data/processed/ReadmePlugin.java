/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
* This is the top-level class of the Readme plugin tool.
*
* @see AbstractUIPlugin for additional information on UI plugins
*/
public class ReadmePlugin extends AbstractUIPlugin {

    //$NON-NLS-1$
    public static final String PLUGIN_ID = "org.eclipse.ui.examples.readmetool";

    /**
* Default instance of the receiver
*/
    private static ReadmePlugin inst;

    /**
* Creates the Readme plugin and caches its default instance
*/
    public  ReadmePlugin() {
        if (inst == null)
            inst = this;
    }

    /**
* Gets the plugin singleton.
*
* @return the default ReadmePlugin instance
*/
    public static ReadmePlugin getDefault() {
        return inst;
    }
}

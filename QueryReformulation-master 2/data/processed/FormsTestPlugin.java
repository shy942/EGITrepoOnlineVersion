/***/
package org.eclipse.ui.tests.forms.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
* The activator class controls the plug-in life cycle
*/
public class FormsTestPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.ui.tests.forms";

    // The shared instance
    private static FormsTestPlugin plugin;

    /**
* The constructor
*/
    public  FormsTestPlugin() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
* Returns the shared instance
*
* @return the shared instance
*/
    public static FormsTestPlugin getDefault() {
        return plugin;
    }
}

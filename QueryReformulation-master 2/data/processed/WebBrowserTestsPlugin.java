/***/
package org.eclipse.ui.tests.browser.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
* The web browser tests plugin class.
*/
public class WebBrowserTestsPlugin extends AbstractUIPlugin {

    // Web browser plugin id
    public static final String PLUGIN_ID = "org.eclipse.ui.browser.tests";

    // singleton instance of this class
    private static WebBrowserTestsPlugin singleton;

    /**
* Create the WebBrowserTestsPlugin
*/
    public  WebBrowserTestsPlugin() {
        super();
        singleton = this;
    }

    /**
* Returns the singleton instance of this plugin.
*
* @return org.eclipse.ui.internal.browser.WebBrowserPlugin
*/
    public static WebBrowserTestsPlugin getInstance() {
        return singleton;
    }
}

/***/
package org.eclipse.ui.internal.browser;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
* The main web browser plugin class.
*/
public class WebBrowserUIPlugin extends AbstractUIPlugin {

    // Web browser plugin id
    //$NON-NLS-1$
    public static final String PLUGIN_ID = "org.eclipse.ui.browser";

    // singleton instance of this class
    private static WebBrowserUIPlugin singleton;

    // cached copy of all browsers
    private static List<BrowserExt> browsers;

    /**
* Create the WebBrowserUIPlugin
*/
    public  WebBrowserUIPlugin() {
        super();
        singleton = this;
    }

    /**
* Returns the singleton instance of this plugin.
*
* @return org.eclipse.ui.internal.browser.WebBrowserPlugin
*/
    public static WebBrowserUIPlugin getInstance() {
        return singleton;
    }

    /**
* Shuts down this plug-in and saves all plug-in state.
*
* @exception Exception
*/
    @Override
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        BrowserManager.safeDispose();
    }

    /**
* Returns an array of all known browers.
* <p>
* A new array is returned on each call, so clients may store or modify the result.
* </p>
*
* @return a possibly-empty array of browser instances {@link IClient}
*/
    public static IBrowserExt[] getBrowsers() {
        if (browsers == null)
            loadBrowsers();
        IBrowserExt[] c = new IBrowserExt[browsers.size()];
        browsers.toArray(c);
        return c;
    }

    public static IBrowserExt findBrowsers(String executable) {
        IBrowserExt[] browsers2 = getBrowsers();
        if (browsers2 == null || executable == null)
            return null;
        //$NON-NLS-1$
        int ind1 = executable.lastIndexOf("/");
        int ind2 = executable.lastIndexOf("\\");
        if (ind2 > ind1)
            ind1 = ind2;
        executable = executable.substring(ind1 + 1);
        String os = Platform.getOS();
        int size = browsers2.length;
        for (int i = 0; i < size; i++) {
            if (browsers2[i].getOS().toLowerCase().indexOf(os) != -1) {
                if (browsers2[i].isAvailable()) {
                    String executable2 = browsers2[i].getExecutable();
                    if (executable.startsWith(executable2))
                        return browsers2[i];
                }
            }
        }
        return null;
    }

    private static synchronized void loadBrowsers() {
        if (browsers != null)
            return;
        Trace.trace(Trace.CONFIG, "->- Loading .browsers extension point ->-");
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] cf = registry.getConfigurationElementsFor(PLUGIN_ID, "browsers");
        int size = cf.length;
        browsers = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            try {
                browsers.add(new BrowserExt(cf[i]));
                Trace.trace(Trace.CONFIG, "  Loaded browser: " + cf[i].getAttribute("id"));
            } catch (Throwable t) {
                Trace.trace(Trace.SEVERE, "  Could not load browser: " + cf[i].getAttribute("id"), t);
            }
        }
        Trace.trace(Trace.CONFIG, "-<- Done loading .browsers extension point -<-");
    }

    public static synchronized void logError(String message, Throwable ex) {
        if (message == null)
            message = "";
        Status errorStatus = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, ex);
        WebBrowserUIPlugin.getInstance().getLog().log(errorStatus);
    }
}

/***/
package org.eclipse.ui.tests.performance;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

/**
* The main plugin class to be used in the desktop.
*/
public class UIPerformancePlugin extends AbstractUIPlugin {

    //The shared instance.
    private static UIPerformancePlugin plugin;

    private BundleContext context;

    /**
* The constructor.
*/
    public  UIPerformancePlugin() {
        plugin = this;
    }

    /**
* This method is called upon plug-in activation
*/
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        this.context = context;
    }

    /**
* This method is called when the plug-in is stopped
*/
    @Override
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        plugin = null;
        context = null;
    }

    /**
* Returns the shared instance.
*
* @return the shared instance.
*/
    public static UIPerformancePlugin getDefault() {
        return plugin;
    }

    /**
* Returns an image descriptor for the image file at the given
* plug-in relative path.
*
* @param path the path
* @return the image descriptor
*/
    public static ImageDescriptor getImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.tests.performance", path);
    }

    public BundleContext getContext() {
        return context;
    }
}

/***/
package org.eclipse.e4.core.commands.tests;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.osgi.service.packageadmin.PackageAdmin;

public class TestActivator implements BundleActivator {

    private static TestActivator plugin = null;

    private IEclipseContext appContext;

    private IEclipseContext serviceContext;

    public static TestActivator getDefault() {
        return plugin;
    }

    @Override
    public void start(BundleContext context) throws Exception {
        plugin = this;
        serviceContext = EclipseContextFactory.getServiceContext(context);
        appContext = serviceContext.createChild();
        addLogService(appContext);
    }

    private void addLogService(IEclipseContext context) {
        context.set(LogService.class, new LogService() {

            @Override
            public void log(int level, String message) {
                System.out.println(level + ": " + message);
            }

            @Override
            public void log(int level, String message, Throwable exception) {
                System.out.println(level + ": " + message);
                if (exception != null) {
                    exception.printStackTrace();
                }
            }

            @SuppressWarnings("rawtypes")
            @Override
            public void log(ServiceReference sr, int level, String message) {
            // Nothing
            }

            @SuppressWarnings("rawtypes")
            @Override
            public void log(ServiceReference sr, int level, String message, Throwable exception) {
            // Nothing
            }
        });
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceContext.dispose();
        plugin = null;
    }

    public IEclipseContext getGlobalContext() {
        return appContext;
    }

    public PackageAdmin getBundleAdmin() {
        return serviceContext.get(PackageAdmin.class);
    }

    public Bundle getBundleForName(String bundleName) {
        Bundle[] bundles = getBundleAdmin().getBundles(bundleName, null);
        if (bundles == null)
            return null;
        // Return the first bundle that is not installed or uninstalled
        for (int i = 0; i < bundles.length; i++) {
            if ((bundles[i].getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0) {
                return bundles[i];
            }
        }
        return null;
    }
}

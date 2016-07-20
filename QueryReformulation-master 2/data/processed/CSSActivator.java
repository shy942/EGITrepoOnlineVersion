/***/
package org.eclipse.e4.ui.internal.css.swt;

import org.eclipse.e4.ui.internal.css.swt.definition.IColorAndFontProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.util.tracker.ServiceTracker;

public class CSSActivator implements BundleActivator {

    private static CSSActivator activator;

    private BundleContext context;

    private ServiceTracker<PackageAdmin, PackageAdmin> pkgAdminTracker;

    private ServiceTracker<LogService, LogService> logTracker;

    private ServiceTracker<IColorAndFontProvider, IColorAndFontProvider> colorAndFontProviderTracker;

    public static CSSActivator getDefault() {
        return activator;
    }

    public PackageAdmin getBundleAdmin() {
        if (pkgAdminTracker == null) {
            if (context == null) {
                return null;
            }
            pkgAdminTracker = new ServiceTracker<PackageAdmin, PackageAdmin>(context, PackageAdmin.class.getName(), null);
            pkgAdminTracker.open();
        }
        return pkgAdminTracker.getService();
    }

    /**
* @param bundleName
*            the bundle id
* @return A bundle if found, or <code>null</code>
*/
    public Bundle getBundleForName(String bundleName) {
        Bundle[] bundles = getBundleAdmin().getBundles(bundleName, null);
        if (bundles == null) {
            return null;
        }
        // Return the first bundle that is not installed or uninstalled
        for (Bundle bundle : bundles) {
            if ((bundle.getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0) {
                return bundle;
            }
        }
        return null;
    }

    @Override
    public void start(BundleContext context) throws Exception {
        activator = this;
        this.context = context;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (pkgAdminTracker != null) {
            pkgAdminTracker.close();
            pkgAdminTracker = null;
        }
        if (logTracker != null) {
            logTracker.close();
            logTracker = null;
        }
        if (colorAndFontProviderTracker != null) {
            colorAndFontProviderTracker.close();
            colorAndFontProviderTracker = null;
        }
        this.context = null;
    }

    private LogService getLogger() {
        if (logTracker == null) {
            if (context == null) {
                return null;
            }
            logTracker = new ServiceTracker<LogService, LogService>(context, LogService.class.getName(), null);
            logTracker.open();
        }
        return logTracker.getService();
    }

    public void log(int logError, String message) {
        LogService logger = getLogger();
        if (logger != null) {
            logger.log(logError, message);
        }
    }

    public void log(int logError, String message, Throwable e) {
        LogService logger = getLogger();
        if (logger != null) {
            logger.log(logError, message, e);
        }
    }

    public IColorAndFontProvider getColorAndFontProvider() {
        if (colorAndFontProviderTracker == null) {
            if (context == null) {
                return null;
            }
            colorAndFontProviderTracker = new ServiceTracker<IColorAndFontProvider, IColorAndFontProvider>(context, IColorAndFontProvider.class.getName(), null);
            colorAndFontProviderTracker.open();
        }
        return colorAndFontProviderTracker.getService();
    }
}

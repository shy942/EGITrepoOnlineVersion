/***/
package org.eclipse.ui.tests.dynamicplugins;

import junit.framework.TestCase;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.PackageAdmin;

public class TestInstallUtil extends TestCase {

    static BundleContext context;

    public static void setContext(BundleContext newContext) {
        context = newContext;
    }

    public static Bundle installBundle(String pluginLocation) throws BundleException, IllegalStateException {
        Bundle target = context.installBundle(pluginLocation);
        int state = target.getState();
        if (state != Bundle.INSTALLED) {
            throw new IllegalStateException("Bundle " + target + " is in a wrong state: " + state);
        }
        refreshPackages(new Bundle[] { target });
        return target;
    }

    public static void uninstallBundle(Bundle target) throws BundleException {
        target.uninstall();
        refreshPackages(null);
    }

    public static void refreshPackages(Bundle[] bundles) {
        ServiceReference packageAdminRef = context.getServiceReference(PackageAdmin.class.getName());
        PackageAdmin packageAdmin = null;
        if (packageAdminRef != null) {
            packageAdmin = (PackageAdmin) context.getService(packageAdminRef);
            if (packageAdmin == null) {
                return;
            }
        }
        final boolean[] flag = new boolean[] { false };
        FrameworkListener listener = new FrameworkListener() {

            @Override
            public void frameworkEvent(FrameworkEvent event) {
                if (event.getType() == FrameworkEvent.PACKAGES_REFRESHED) {
                    synchronized (flag) {
                        flag[0] = true;
                        flag.notifyAll();
                    }
                }
            }
        };
        context.addFrameworkListener(listener);
        packageAdmin.refreshPackages(bundles);
        synchronized (flag) {
            while (!flag[0]) {
                try {
                    flag.wait();
                } catch (InterruptedException e) {
                }
            }
        }
        context.removeFrameworkListener(listener);
        context.ungetService(packageAdminRef);
    }
}

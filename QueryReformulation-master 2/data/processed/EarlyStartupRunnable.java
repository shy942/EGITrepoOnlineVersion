/***/
package org.eclipse.ui.internal;

import com.ibm.icu.text.MessageFormat;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.misc.UIStats;

/**
* A utility class used to call #earlyStartup on the proper instance for a given
* configuration element.
*
* @since 3.0
*/
public class EarlyStartupRunnable extends SafeRunnable {

    private IExtension extension;

    /**
* @param extension
*            must not be null
*/
    public  EarlyStartupRunnable(IExtension extension) {
        this.extension = extension;
    }

    @Override
    public void run() throws Exception {
        IConfigurationElement[] configElements = extension.getConfigurationElements();
        if (configElements.length == 0) {
            missingStartupElementMessage(//$NON-NLS-1$
            "The org.eclipse.ui.IStartup extension from '" + extension.getNamespaceIdentifier() + //$NON-NLS-1$
            "' does not provide a valid '" + IWorkbenchConstants.TAG_STARTUP + //$NON-NLS-1$
            "' element.");
        }
        // look for the startup tag in each element and run the extension
        for (IConfigurationElement element : configElements) {
            if (element != null && element.getName().equals(IWorkbenchConstants.TAG_STARTUP)) {
                runEarlyStartup(WorkbenchPlugin.createExtension(element, IWorkbenchConstants.TAG_CLASS));
            }
        }
    }

    private void missingStartupElementMessage(String message) {
        IStatus status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, message, null);
        WorkbenchPlugin.log(status);
    }

    @Override
    public void handleException(Throwable exception) {
        IStatus status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, //$NON-NLS-1$
        "Unable to execute early startup code for the org.eclipse.ui.IStartup extension contributed by the '" + extension.getNamespaceIdentifier() + //$NON-NLS-1$
        "' plug-in.", exception);
        WorkbenchPlugin.log(status);
    }

    private void runEarlyStartup(Object executableExtension) {
        if (executableExtension instanceof IStartup) {
            //$NON-NLS-1$
            String methodName = executableExtension.getClass().getName() + ".earlyStartup";
            try {
                UIStats.start(UIStats.EARLY_STARTUP, methodName);
                ((IStartup) executableExtension).earlyStartup();
            } finally {
                UIStats.end(UIStats.EARLY_STARTUP, executableExtension, methodName);
            }
        } else {
            String message = executableExtension == null ? //$NON-NLS-1$
            "The org.eclipse.ui.IStartup extension from '" + extension.getNamespaceIdentifier() + //$NON-NLS-1$
            "' does not provide a valid class attribute." : //$NON-NLS-1$
            MessageFormat.format(//$NON-NLS-1$
            "Startup class {0} must implement org.eclipse.ui.IStartup", executableExtension.getClass().getName());
            IStatus status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, message, null);
            WorkbenchPlugin.log(status);
        }
    }
}

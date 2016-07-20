/***/
package org.eclipse.ui.internal.wizards;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* Registry that contains wizards contributed via the <code>newWizards</code>
* extension point.
*
* @since 3.1
*/
public final class NewWizardRegistry extends AbstractExtensionWizardRegistry {

    private static NewWizardRegistry singleton;

    /**
* Return the singleton instance of this class.
*
* @return the singleton instance of this class
*/
    public static synchronized NewWizardRegistry getInstance() {
        if (singleton == null) {
            singleton = new NewWizardRegistry();
        }
        return singleton;
    }

    /**
* Private constructor.
*/
    private  NewWizardRegistry() {
        super();
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_NEW;
    }

    @Override
    protected String getPlugin() {
        return PlatformUI.PLUGIN_ID;
    }
}
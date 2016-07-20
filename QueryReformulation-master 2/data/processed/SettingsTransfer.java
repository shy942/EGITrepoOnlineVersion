/***/
package org.eclipse.ui.preferences;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.internal.preferences.SettingsTransferRegistryReader;

/**
* The SettingsTransfer is the abstract superclass of settings transfers
* used when switching workspaces.
* @since 3.3
*
*/
public abstract class SettingsTransfer {

    /**
* Return the configuration elements for all of the settings
* transfers.
* @return IConfigurationElement[]
*/
    public static IConfigurationElement[] getSettingsTransfers() {
        return (new SettingsTransferRegistryReader()).getSettingTransfers();
    }

    /**
* Transfer the settings to a workspace rooted at newWorkspacwe
* @param newWorkspaceRoot
* @return {@link IStatus} the status of the transfer.
*/
    public abstract IStatus transferSettings(IPath newWorkspaceRoot);

    /**
* Return the name for the receiver.
* @return String
*/
    public abstract String getName();
}

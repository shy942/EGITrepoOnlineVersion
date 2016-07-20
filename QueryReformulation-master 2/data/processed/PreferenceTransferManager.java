/***/
package org.eclipse.ui.internal.preferences;

import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.registry.PreferenceTransferRegistryReader;

/**
* Manages preference transfer support for the workbench
*
* @since 3.1
*/
public class PreferenceTransferManager {

    /**
* Return an array of <code>IPreferenceTransfer</code> objects
* @return an array of <code>IPreferenceTransfer</code> objects
*/
    public static PreferenceTransferElement[] getPreferenceTransfers() {
        return new PreferenceTransferRegistryReader(IWorkbenchRegistryConstants.PL_PREFERENCE_TRANSFER).getPreferenceTransfers();
    }
}

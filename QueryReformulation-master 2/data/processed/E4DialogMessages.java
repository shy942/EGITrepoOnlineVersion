/***/
package org.eclipse.e4.ui.dialogs.textbundles;

import org.eclipse.osgi.util.NLS;

/**
* Based on org.eclipse.ui.internal.WorkbenchMessages
*/
public class E4DialogMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.e4.ui.dialogs.textbundles.messages";

    public static String FilteredTree_AccessibleListenerClearButton;

    public static String FilteredTree_ClearToolTip;

    public static String FilteredTree_FilterMessage;

    public static String FilteredTree_AccessibleListenerFiltered;

    static {
        // load message values from bundle file
        reloadMessages();
    }

    public static void reloadMessages() {
        NLS.initializeMessages(BUNDLE_NAME, E4DialogMessages.class);
    }
}

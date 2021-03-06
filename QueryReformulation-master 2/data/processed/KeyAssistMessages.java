/***/
package org.eclipse.ui.internal.keys;

import org.eclipse.osgi.util.NLS;

/**
* The KeyAssistMessages class is the class that manages the messages
* used in the KeyAssistDialog.
*
*/
public class KeyAssistMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.keys.KeyAssistDialog";

    public static String NoMatches_Message;

    public static String openPreferencePage;

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, KeyAssistMessages.class);
    }
}

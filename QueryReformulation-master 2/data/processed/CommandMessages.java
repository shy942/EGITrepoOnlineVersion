/***/
package org.eclipse.ui.internal.menus;

import org.eclipse.osgi.util.NLS;

/**
*
* @since 3.5
*
*/
public class CommandMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.menus.messages";

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, CommandMessages.class);
    }

    public static String Tooltip_Accelerator;
}

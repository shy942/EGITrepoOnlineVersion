/***/
package org.eclipse.e4.ui.internal.workbench.renderers.swt;

import org.eclipse.osgi.util.NLS;

/**
* SWTRenderers message catalog
*/
public class SWTRenderersMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.e4.ui.internal.workbench.renderers.swt.messages";

    public static String choosePartsToSaveTitle;

    public static String choosePartsToSave;

    public static String menuClose;

    public static String menuCloseOthers;

    public static String menuCloseAll;

    public static String menuCloseRight;

    public static String menuCloseLeft;

    public static String viewMenu;

    static {
        // load message values from bundle file
        reloadMessages();
    }

    public static void reloadMessages() {
        NLS.initializeMessages(BUNDLE_NAME, SWTRenderersMessages.class);
    }
}

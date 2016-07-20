/***/
package org.eclipse.ui.internal.views.contentoutline;

import org.eclipse.osgi.util.NLS;

/**
* ContentOutlineMessages is the message class for the messages used in the content outline.
*
*/
public class ContentOutlineMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.views.contentoutline.messages";

    // ==============================================================================
    // Outline View
    // ==============================================================================
    /** */
    public static String ContentOutline_noOutline;

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, ContentOutlineMessages.class);
    }
}

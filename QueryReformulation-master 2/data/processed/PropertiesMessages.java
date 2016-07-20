/***/
package org.eclipse.ui.internal.views.properties;

import org.eclipse.osgi.util.NLS;

/**
*/
public class PropertiesMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.views.properties.messages";

    // package: org.eclipse.ui.views.properties
    // ==============================================================================
    // Properties View
    // ==============================================================================
    /** */
    public static String Categories_text;

    /** */
    public static String Categories_toolTip;

    /** */
    public static String Columns_text;

    /** */
    public static String Columns_toolTip;

    /** */
    public static String CopyProperty_text;

    /** */
    public static String Defaults_text;

    /** */
    public static String Defaults_toolTip;

    /** */
    public static String Filter_text;

    /** */
    public static String Filter_toolTip;

    /** */
    public static String Selection_description;

    /** */
    public static String Pin_text;

    /** */
    public static String Pin_toolTip;

    /** */
    public static String PropertyViewer_property;

    /** */
    public static String PropertyViewer_value;

    /** */
    public static String PropertyViewer_misc;

    /** */
    public static String CopyToClipboardProblemDialog_title;

    /** */
    public static String CopyToClipboardProblemDialog_message;

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, PropertiesMessages.class);
    }
}

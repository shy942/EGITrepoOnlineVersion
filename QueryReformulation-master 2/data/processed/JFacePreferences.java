/***/
package org.eclipse.jface.preference;

/**
*
* JFacePreferences is a class used to administer the preferences used by JFace
* objects.
*/
public final class JFacePreferences {

    /**
* Identifier for the Error Color
*/
    //$NON-NLS-1$
    public static final String ERROR_COLOR = "ERROR_COLOR";

    /**
* Identifier for the Hyperlink Color
*/
    //$NON-NLS-1$
    public static final String HYPERLINK_COLOR = "HYPERLINK_COLOR";

    /**
* Identifier for the Active Hyperlink Colour
*/
    //$NON-NLS-1$
    public static final String ACTIVE_HYPERLINK_COLOR = "ACTIVE_HYPERLINK_COLOR";

    /**
* Identifier for the color used to show extra informations in labels, as a
* qualified name. For example in 'Foo.txt - myproject/bar', the qualifier
* is '- myproject/bar'.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String QUALIFIER_COLOR = "QUALIFIER_COLOR";

    /**
* Identifier for the color used to show label decorations For example in
* 'Foo.txt [1.16]', the decoration is '[1.16]'.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String DECORATIONS_COLOR = "DECORATIONS_COLOR";

    /**
* Identifier for the color used to counter informations For example in
* 'Foo.txt (2 matches)', the counter information is '(2 matches)'.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String COUNTER_COLOR = "COUNTER_COLOR";

    /**
* Identifier for the color used for the background of content assist
* popup dialogs.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String CONTENT_ASSIST_BACKGROUND_COLOR = "CONTENT_ASSIST_BACKGROUND_COLOR";

    /**
* Identifier for the color used for the foreground of content assist
* popup dialogs.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String CONTENT_ASSIST_FOREGROUND_COLOR = "CONTENT_ASSIST_FOREGROUND_COLOR";

    private static IPreferenceStore preferenceStore;

    /**
* Prevent construction.
*/
    private  JFacePreferences() {
    }

    /**
* Return the preference store for the receiver.
*
* @return IPreferenceStore or null
*/
    public static IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    /**
* Set the preference store for the receiver.
*
* @param store
*            IPreferenceStore
*/
    public static void setPreferenceStore(IPreferenceStore store) {
        preferenceStore = store;
    }
}

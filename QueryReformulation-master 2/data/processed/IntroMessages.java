/***/
package org.eclipse.ui.internal.intro;

import org.eclipse.osgi.util.NLS;

/**
* The IntroMessages are the messages used in the intro support.
*/
public class IntroMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.intro.intro";

    public static String Intro_could_not_create_part;

    public static String Intro_could_not_create_proxy;

    public static String Intro_could_not_create_descriptor;

    public static String Intro_action_text;

    public static String Intro_default_title;

    public static String Intro_missing_product_title;

    public static String Intro_missing_product_message;

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, IntroMessages.class);
    }
}

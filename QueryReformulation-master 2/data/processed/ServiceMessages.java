/***/
package org.eclipse.e4.ui.internal.services;

import org.eclipse.osgi.util.NLS;

public class ServiceMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.e4.ui.internal.services.serviceMessages";

    // Event broker
    public static String NO_EVENT_ADMIN;

    public static String NO_BUNDLE_CONTEXT;

    static {
        // load message values from bundle file
        reloadMessages();
    }

    public static void reloadMessages() {
        NLS.initializeMessages(BUNDLE_NAME, ServiceMessages.class);
    }
}

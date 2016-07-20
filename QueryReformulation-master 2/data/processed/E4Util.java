/***/
package org.eclipse.ui.internal.e4.compatibility;

import org.eclipse.osgi.service.debug.DebugOptions;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
*
*/
public class E4Util {

    // debug tracing
    //$NON-NLS-1$;
    private static final String OPTION_DEBUG_E4 = "org.eclipse.ui.workbench/debug/e4";

    public static final boolean DEBUG_E4;

    static {
        WorkbenchPlugin activator = WorkbenchPlugin.getDefault();
        if (activator == null)
            DEBUG_E4 = false;
        else {
            DebugOptions debugOptions = activator.getDebugOptions();
            if (debugOptions == null)
                DEBUG_E4 = false;
            else
                DEBUG_E4 = debugOptions.getBooleanOption(OPTION_DEBUG_E4, false);
        }
    }

    public static void unsupported(String msg) throws UnsupportedOperationException {
        if (DEBUG_E4)
            //$NON-NLS-1$
            WorkbenchPlugin.log("unsupported: " + msg);
    }

    public static void message(String msg) throws UnsupportedOperationException {
        if (DEBUG_E4)
            WorkbenchPlugin.log(msg);
    }
}

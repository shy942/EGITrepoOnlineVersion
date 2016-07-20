/***/
package org.eclipse.ui.internal.navigator.framelist;

import org.eclipse.ui.PlatformUI;

/*package*/
interface IFrameListHelpContextIds {

    //$NON-NLS-1$
    public static final String PREFIX = PlatformUI.PLUGIN_ID + ".";

    // Actions
    //$NON-NLS-1$
    public static final String BACK_ACTION = PREFIX + "back_action_context";

    public static final String FORWARD_ACTION = PREFIX + //$NON-NLS-1$
    "forward_action_context";

    public static final String GO_INTO_ACTION = PREFIX + //$NON-NLS-1$
    "go_into_action_context";

    //$NON-NLS-1$
    public static final String UP_ACTION = PREFIX + "up_action_context";
}

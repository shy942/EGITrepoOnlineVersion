/***/
package org.eclipse.ui.internal.navigator;

import org.eclipse.ui.PlatformUI;

/**
* The help context ids used by the Common Navigator. These are defined in the
* workbench plugin. There is no separate help for the Common Navigator.
*
* @since 3.2
*
*/
public interface INavigatorHelpContextIds {

    // Note we are using the workbench help since help requires the
    // workbench
    //$NON-NLS-1$
    public static final String PREFIX = PlatformUI.PLUGIN_ID + ".";

    /** */
    public static final String GOTO_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "goto_resource_action_context";

    /** */
    public static final String TEXT_CUT_ACTION = PREFIX + //$NON-NLS-1$
    "text_cut_action_context";

    /** */
    public static final String TEXT_COPY_ACTION = PREFIX + //$NON-NLS-1$
    "text_copy_action_context";

    /** */
    public static final String TEXT_PASTE_ACTION = PREFIX + //$NON-NLS-1$
    "text_paste_action_context";

    /** */
    public static final String TEXT_DELETE_ACTION = PREFIX + //$NON-NLS-1$
    "text_delete_action_context";

    /** */
    public static final String TEXT_SELECT_ALL_ACTION = PREFIX + //$NON-NLS-1$
    "text_select_all_action_context";

    // Dialogs
    /** */
    public static final String GOTO_RESOURCE_DIALOG = PREFIX + //$NON-NLS-1$
    "goto_resource_dialog_context";
}

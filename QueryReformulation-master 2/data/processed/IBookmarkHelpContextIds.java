/***/
package org.eclipse.ui.views.bookmarkexplorer;

import org.eclipse.ui.PlatformUI;

/**
* Help context ids for the bookmark view.
* <p>
* This interface contains constants only; it is not intended to be implemented
* or extended.
* </p>
*
*/
interface IBookmarkHelpContextIds {

    //$NON-NLS-1$
    public static final String PREFIX = PlatformUI.PLUGIN_ID + ".";

    // Actions
    public static final String COPY_BOOKMARK_ACTION = PREFIX + //$NON-NLS-1$
    "copy_bookmark_action_context";

    public static final String PASTE_BOOKMARK_ACTION = PREFIX + //$NON-NLS-1$
    "paste_bookmark_action_context";

    public static final String REMOVE_BOOKMARK_ACTION = PREFIX + //$NON-NLS-1$
    "remove_bookmark_action_context";

    public static final String OPEN_BOOKMARK_ACTION = PREFIX + //$NON-NLS-1$
    "open_bookmark_action_context";

    public static final String SELECT_ALL_BOOKMARK_ACTION = PREFIX + //$NON-NLS-1$
    "select_all_bookmark_action_context";

    public static final String BOOKMARK_PROPERTIES_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_properties_action_context";

    public static final String SORT_ASCENDING_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_ascending_action_context";

    public static final String SORT_DESCENDING_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_descending_action_context";

    public static final String SORT_DESCRIPTION_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_description_action_context";

    public static final String SORT_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_resource_action_context";

    public static final String SORT_FOLDER_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_folder_action_context";

    public static final String SORT_LOCATION_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_location_action_context";

    public static final String SORT_CREATION_TIME_ACTION = PREFIX + //$NON-NLS-1$
    "bookmark_sort_creation_time_action_context";

    // Views
    //$NON-NLS-1$
    public static final String BOOKMARK_VIEW = PREFIX + "bookmark_view_context";
}

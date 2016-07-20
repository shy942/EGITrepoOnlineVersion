/***/
package org.eclipse.ui.views.tasklist;

import org.eclipse.ui.PlatformUI;

/**
* Help context ids for the task list view.
* <p>
* This interface contains constants only; it is not intended to be implemented
* or extended.
* </p>
*
*/
interface ITaskListHelpContextIds {

    //$NON-NLS-1$
    public static final String PREFIX = PlatformUI.PLUGIN_ID + ".";

    // Actions
    public static final String PURGE_COMPLETED_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "purge_completed_task_action_context";

    public static final String COPY_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "copy_task_action_context";

    public static final String PASTE_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "paste_task_action_context";

    public static final String NEW_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "new_task_action_context";

    public static final String REMOVE_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "remove_task_action_context";

    public static final String GOTO_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "goto_task_action_context";

    public static final String FILTERS_ACTION = PREFIX + //$NON-NLS-1$
    "filters_action_context";

    public static final String MARK_COMPLETED_ACTION = PREFIX + //$NON-NLS-1$
    "mark_completed_action_context";

    public static final String RESOLVE_MARKER_ACTION = PREFIX + //$NON-NLS-1$
    "resolve_marker_action_context";

    public static final String SELECT_ALL_TASKS_ACTION = PREFIX + //$NON-NLS-1$
    "select_all_tasks_action_context";

    public static final String TASK_PROPERTIES_ACTION = PREFIX + //$NON-NLS-1$
    "task_properties_action_context";

    public static final String TASK_SORT_TYPE_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_type_action_context";

    public static final String TASK_SORT_COMPLETED_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_completed_action_context";

    public static final String TASK_SORT_PRIORITY_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_priority_action_context";

    public static final String TASK_SORT_DESCRIPTION_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_description_action_context";

    public static final String TASK_SORT_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_resource_action_context";

    public static final String TASK_SORT_FOLDER_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_folder_action_context";

    public static final String TASK_SORT_LOCATION_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_location_action_context";

    public static final String TASK_SORT_CREATION_TIME_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_creation_time_action_context";

    public static final String TASK_SORT_ASCENDING_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_ascending_action_context";

    public static final String TASK_SORT_DESCENDING_ACTION = PREFIX + //$NON-NLS-1$
    "task_sort_descending_action_context";

    // Dialogs
    public static final String FILTERS_DIALOG = PREFIX + //$NON-NLS-1$
    "task_filters_dialog_context";

    public static final String PROPERTIES_DIALOG = PREFIX + //$NON-NLS-1$
    "task_properties_dialog_context";

    // Views
    public static final String TASK_LIST_VIEW = PREFIX + //$NON-NLS-1$
    "task_list_view_context";
}

/***/
package org.eclipse.ui.internal.ide;

/**
* Help context ids for the workbench.
* <p>
* This interface contains constants only; it is not intended to be implemented
* or extended.
* </p>
*/
public interface IIDEHelpContextIds {

    //$NON-NLS-1$
    public static final String PREFIX = IDEWorkbenchPlugin.IDE_WORKBENCH + ".";

    // Actions
    public static final String ADD_BOOKMARK_ACTION = PREFIX + //$NON-NLS-1$
    "add_bookmark_action_context";

    public static final String ADD_TASK_ACTION = PREFIX + //$NON-NLS-1$
    "add_task_action_context";

    public static final String INCREMENTAL_BUILD_ACTION = PREFIX + //$NON-NLS-1$
    "incremental_build_action_context";

    public static final String FULL_BUILD_ACTION = PREFIX + //$NON-NLS-1$
    "full_build_action_context";

    public static final String CLOSE_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "close_resource_action_context";

    public static final String CLOSE_UNRELATED_PROJECTS_ACTION = PREFIX + //$NON-NLS-1$
    "close_unrelated_projects_action_context";

    public static final String OPEN_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "open_resource_action_context";

    public static final String OPEN_FILE_ACTION = PREFIX + //$NON-NLS-1$
    "open_file_action_context";

    public static final String OPEN_LOCAL_FILE_ACTION = PREFIX + //$NON-NLS-1$
    "open_local_file_action_context";

    public static final String OPEN_SYSTEM_EDITOR_ACTION = PREFIX + //$NON-NLS-1$
    "open_system_editor_action_context";

    public static final String REFRESH_ACTION = PREFIX + //$NON-NLS-1$
    "refresh_action_context";

    public static final String MOVE_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "move_resource_action_context";

    public static final String COPY_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "copy_resource_action_context";

    public static final String MOVE_PROJECT_ACTION = PREFIX + //$NON-NLS-1$
    "move_project_action_context";

    public static final String COPY_PROJECT_ACTION = PREFIX + //$NON-NLS-1$
    "copy_project_action_context";

    public static final String RENAME_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "rename_resource_action_context";

    public static final String DELETE_RESOURCE_ACTION = PREFIX + //$NON-NLS-1$
    "delete_resource_action_context";

    public static final String PROJECT_PROPERTY_DIALOG_ACTION = PREFIX + //$NON-NLS-1$
    "project_property_dialog_action_context";

    public static final String CREATE_FOLDER_ACTION = PREFIX + //$NON-NLS-1$
    "create_folder_action_context";

    public static final String CREATE_FILE_ACTION = PREFIX + //$NON-NLS-1$
    "create_file_action_context";

    public static final String SCRUB_LOCAL_ACTION = PREFIX + //$NON-NLS-1$
    "scrub_local_action_context";

    public static final String GLOBAL_INCREMENTAL_BUILD_ACTION = PREFIX + //$NON-NLS-1$
    "global_incremental_build_action_context";

    public static final String GLOBAL_FULL_BUILD_ACTION = PREFIX + //$NON-NLS-1$
    "global_full_build_action_context";

    public static final String QUICK_START_ACTION = PREFIX + //$NON-NLS-1$
    "quick_start_action_context";

    public static final String TIPS_AND_TRICKS_ACTION = PREFIX + //$NON-NLS-1$
    "tips_and_tricks_action_context";

    public static final String TEXT_CUT_ACTION = PREFIX + //$NON-NLS-1$
    "text_cut_action_context";

    public static final String TEXT_COPY_ACTION = PREFIX + //$NON-NLS-1$
    "text_copy_action_context";

    public static final String TEXT_PASTE_ACTION = PREFIX + //$NON-NLS-1$
    "text_paste_action_context";

    public static final String TEXT_DELETE_ACTION = PREFIX + //$NON-NLS-1$
    "text_delete_action_context";

    public static final String TEXT_SELECT_ALL_ACTION = PREFIX + //$NON-NLS-1$
    "text_select_all_action_context";

    public static final String OPEN_WORKSPACE_FILE_ACTION = PREFIX + //$NON-NLS-1$
    "open_workspace_file_action_context";

    // Dialogs
    public static final String PROJECT_LOCATION_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "project_location_selection_dialog_context";

    public static final String CONTAINER_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "container_selection_dialog_context";

    public static final String FILE_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "file_selection_dialog_context";

    public static final String RESOURCE_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "resource_selection_dialog_context";

    public static final String DELETE_PROJECT_DIALOG = PREFIX + //$NON-NLS-1$
    "delete_project_dialog_context";

    public static final String MARKER_RESOLUTION_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "marker_resolution_selection_dialog_context";

    public static final String WELCOME_PAGE_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "welcome_page_selection_dialog";

    public static final String TIPS_AND_TRICKS_PAGE_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "tips_and_tricks_page_selection_dialog";

    public static final String OPEN_RESOURCE_DIALOG = PREFIX + //$NON-NLS-1$
    "open_resource_dialog";

    //$NON-NLS-1$
    public static final String NEW_FOLDER_DIALOG = PREFIX + "new_folder_dialog";

    public static final String PATH_VARIABLE_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "path_variable_selection_dialog";

    public static final String IMPORT_TYPE_DIALOG = PREFIX + //$NON-NLS-1$
    "import_type_dialog";

    public static final String SAVE_AS_DIALOG = PREFIX + //$NON-NLS-1$
    "save_as_dialog_context";

    // Editors
    public static final String WELCOME_EDITOR = PREFIX + //$NON-NLS-1$
    "welcome_editor_context";

    // Preference pages
    public static final String BUILD_ORDER_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "build_order_preference_page_context";

    public static final String FILE_STATES_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "file_states_preference_page_context";

    public static final String LINKED_RESOURCE_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "linked_resource_preference_page_context";

    // Property pages
    public static final String PROJECT_REFERENCE_PROPERTY_PAGE = PREFIX + //$NON-NLS-1$
    "project_reference_property_page_context";

    public static final String RESOURCE_FILTER_PROPERTY_PAGE = PREFIX + //$NON-NLS-1$
    "resource_filter_property_page_context";

    public static final String EDIT_RESOURCE_FILTER_PROPERTY_PAGE = PREFIX + //$NON-NLS-1$
    "edit_resource_filter_property_page_context";

    public static final String EDIT_RESOURCE_FILTER_DIALOG = PREFIX + //$NON-NLS-1$
    "edit_resource_filter_dialog_context";

    public static final String RESOURCE_INFO_PROPERTY_PAGE = PREFIX + //$NON-NLS-1$
    "resource_info_property_page_context";

    public static final String NEW_FILE_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_file_wizard_page_context";

    // Wizard pages
    public static final String NEW_PROJECT_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_project_wizard_page_context";

    public static final String NEW_PROJECT_REFERENCE_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_project_reference_wizard_page_context";

    public static final String NEW_FOLDER_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_folder_wizard_page_context";

    public static final String LINKED_RESOURCE_PAGE = PREFIX + //$NON-NLS-1$
    "linked_resource_page_context";

    public static final String NEW_GROUP_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_group_wizard_page_context";

    public static final String NEW_LINK_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_link_wizard_page_context";

    public static final String WORKING_SET_RESOURCE_PAGE = PREFIX + //$NON-NLS-1$
    "working_set_resource_page";

    public static final String WORKSPACE_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "workspace_preference_page_context";

    // Wizards
    public static final String NEW_FILE_WIZARD = PREFIX + //$NON-NLS-1$
    "new_file_wizard_context";

    public static final String NEW_FOLDER_WIZARD = PREFIX + //$NON-NLS-1$
    "new_folder_wizard_context";

    public static final String NEW_PROJECT_WIZARD = PREFIX + //$NON-NLS-1$
    "new_project_wizard_context";

    public static final String SWITCH_WORKSPACE_ACTION = PREFIX + //$NON-NLS-1$
    "switch_workspace_dialog_context";
}

/***/
package org.eclipse.ui.internal;

import org.eclipse.ui.PlatformUI;

/**
* Help context ids for the workbench.
* <p>
* This interface contains constants only; it is not intended to be implemented
* or extended.
* </p>
*
* @issue this class has been xcloned to org.eclipse.ui.internal.ide package;
*        remove all IDE-specific constants from here
*/
public interface IWorkbenchHelpContextIds {

    //$NON-NLS-1$
    public static final String PREFIX = PlatformUI.PLUGIN_ID + ".";

    // Missing context help
    //$NON-NLS-1$
    public static final String MISSING = PREFIX + "missing";

    // Actions
    public static final String DOCK_ON_PERSPECTIVE_ACTION = PREFIX + //$NON-NLS-1$
    "dock_on_perspective_action_context";

    public static final String SHOW_TEXT_PERSPECTIVE_ACTION = PREFIX + //$NON-NLS-1$
    "show_text_perspective_action_context";

    public static final String PROPERTY_DIALOG_ACTION = PREFIX + //$NON-NLS-1$
    "property_dialog_action_context";

    //$NON-NLS-1$
    public static final String NEW_ACTION = PREFIX + "new_action_context";

    //$NON-NLS-1$
    public static final String IMPORT_ACTION = PREFIX + "import_action_context";

    //$NON-NLS-1$
    public static final String EXPORT_ACTION = PREFIX + "export_action_context";

    public static final String SAVE_PERSPECTIVE_ACTION = PREFIX + //$NON-NLS-1$
    "save_perspective_action_context";

    public static final String SAVE_AS_ACTION = PREFIX + //$NON-NLS-1$
    "save_as_action_context";

    public static final String SAVE_ALL_ACTION = PREFIX + //$NON-NLS-1$
    "save_all_action_context";

    //$NON-NLS-1$
    public static final String SAVE_ACTION = PREFIX + "save_action_context";

    //$NON-NLS-1$
    public static final String ABOUT_ACTION = PREFIX + "about_action_context";

    public static final String CLOSE_ALL_ACTION = PREFIX + //$NON-NLS-1$
    "close_all_action_context";

    public static final String CLOSE_OTHERS_ACTION = PREFIX + //$NON-NLS-1$
    "close_others_action_context";

    public static final String LOCK_TOOLBAR_ACTION = PREFIX + //$NON-NLS-1$
    "lock_toolbar_action_context";

    public static final String CLOSE_PAGE_ACTION = PREFIX + //$NON-NLS-1$
    "close_page_action_context";

    public static final String CLOSE_PART_ACTION = PREFIX + //$NON-NLS-1$
    "close_part_action_context";

    public static final String EDIT_ACTION_SETS_ACTION = PREFIX + //$NON-NLS-1$
    "edit_action_sets_action_context";

    public static final String DELETE_RETARGET_ACTION = PREFIX + //$NON-NLS-1$
    "delete_retarget_action_context";

    public static final String CLOSE_ALL_PAGES_ACTION = PREFIX + //$NON-NLS-1$
    "close_all_pages_action_context";

    public static final String OPEN_NEW_WINDOW_ACTION = PREFIX + //$NON-NLS-1$
    "open_new_window_action_context";

    public static final String OPEN_PREFERENCES_ACTION = PREFIX + //$NON-NLS-1$
    "open_preferences_action_context";

    //$NON-NLS-1$
    public static final String QUIT_ACTION = PREFIX + "quit_action_context";

    public static final String RESET_PERSPECTIVE_ACTION = PREFIX + //$NON-NLS-1$
    "reset_perspective_action_context";

    public static final String TOGGLE_EDITORS_VISIBILITY_ACTION = PREFIX + //$NON-NLS-1$
    "target_editors_visibility_action_context";

    public static final String SHOW_VIEW_ACTION = PREFIX + //$NON-NLS-1$
    "show_view_action_context";

    public static final String SHOW_VIEW_OTHER_ACTION = PREFIX + //$NON-NLS-1$
    "show_view_other_action_context";

    public static final String OPEN_PERSPECTIVE_ACTION = PREFIX + //$NON-NLS-1$
    "open_perspective_action_context";

    public static final String CLOSE_ALL_SAVED_ACTION = PREFIX + //$NON-NLS-1$
    "close_all_saved_action_context";

    public static final String SHOW_VIEW_MENU_ACTION = PREFIX + //$NON-NLS-1$
    "show_view_menu_action_context";

    public static final String WORKBENCH_EDITORS_ACTION = PREFIX + //$NON-NLS-1$
    "workbench_editors_action_context";

    public static final String CELL_CUT_ACTION = PREFIX + //$NON-NLS-1$
    "cell_cut_action_context";

    public static final String CELL_COPY_ACTION = PREFIX + //$NON-NLS-1$
    "cell_copy_action_context";

    public static final String CELL_PASTE_ACTION = PREFIX + //$NON-NLS-1$
    "cell_paste_action_context";

    public static final String CELL_DELETE_ACTION = PREFIX + //$NON-NLS-1$
    "cell_delete_action_context";

    public static final String CELL_FIND_ACTION = PREFIX + //$NON-NLS-1$
    "cell_find_action_context";

    public static final String CELL_SELECT_ALL_ACTION = PREFIX + //$NON-NLS-1$
    "cell_select_all_action_context";

    public static final String CELL_UNDO_ACTION = PREFIX + //$NON-NLS-1$
    "cell_undo_action_context";

    public static final String CELL_REDO_ACTION = PREFIX + //$NON-NLS-1$
    "cell_redo_action_context";

    public static final String SHOW_PART_PANE_MENU_ACTION = PREFIX + //$NON-NLS-1$
    "show_part_pane_menu_action_context";

    public static final String CYCLE_PART_FORWARD_ACTION = PREFIX + //$NON-NLS-1$
    "cycle_part_forward_action_context";

    public static final String CYCLE_PART_BACKWARD_ACTION = PREFIX + //$NON-NLS-1$
    "cycle_part_backward_action_context";

    public static final String CYCLE_EDITOR_FORWARD_ACTION = PREFIX + //$NON-NLS-1$
    "cycle_editor_forward_action_context";

    public static final String CYCLE_EDITOR_BACKWARD_ACTION = PREFIX + //$NON-NLS-1$
    "cycle_editor_backward_action_context";

    public static final String CYCLE_PERSPECTIVE_FORWARD_ACTION = PREFIX + //$NON-NLS-1$
    "cycle_perspective_forward_action_context";

    public static final String CYCLE_PERSPECTIVE_BACKWARD_ACTION = PREFIX + //$NON-NLS-1$
    "cycle_perspective_backward_action_context";

    public static final String ACTIVATE_EDITOR_ACTION = PREFIX + //$NON-NLS-1$
    "activate_editor_action_context";

    public static final String MAXIMIZE_PART_ACTION = PREFIX + //$NON-NLS-1$
    "maximize_part_action_context";

    public static final String MINIMIZE_PART_ACTION = PREFIX + //$NON-NLS-1$
    "minimize_part_action_context";

    public static final String SELECT_WORKING_SET_ACTION = PREFIX + //$NON-NLS-1$
    "select_working_set_action_context";

    public static final String CLEAR_WORKING_SET_ACTION = PREFIX + //$NON-NLS-1$
    "clear_working_set_action_context";

    public static final String EDIT_WORKING_SET_ACTION = PREFIX + //$NON-NLS-1$
    "edit_working_set_action_context";

    public static final String SHOW_IN_ACTION = PREFIX + //$NON-NLS-1$
    "show_in_action_context";

    public static final String NAVIGATION_HISTORY_FORWARD = PREFIX + //$NON-NLS-1$
    "navigation_history_forward";

    public static final String NAVIGATION_HISTORY_BACKWARD = PREFIX + //$NON-NLS-1$
    "navigation_history_backward";

    public static final String HELP_CONTENTS_ACTION = PREFIX + //$NON-NLS-1$
    "help_contents_action_context";

    public static final String HELP_SEARCH_ACTION = PREFIX + //$NON-NLS-1$
    "help_search_action_context";

    public static final String DYNAMIC_HELP_ACTION = PREFIX + //$NON-NLS-1$
    "dynamic_help_action_context";

    //$NON-NLS-1$
    public static final String INTRO_ACTION = PREFIX + "intro_action_context";

    // // Dialogs
    //$NON-NLS-1$
    public static final String ABOUT_DIALOG = PREFIX + "about_dialog_context";

    public static final String ABOUT_PLUGINS_DIALOG = PREFIX + //$NON-NLS-1$
    "about_plugins_dialog_context";

    public static final String ABOUT_FEATURES_PLUGINS_DIALOG = PREFIX + //$NON-NLS-1$
    "about_features_plugins_dialog_context";

    public static final String ABOUT_FEATURES_DIALOG = PREFIX + //$NON-NLS-1$
    "about_features_dialog_context";

    public static final String SYSTEM_SUMMARY_DIALOG = PREFIX + //$NON-NLS-1$
    "system_summary_dialog_context";

    public static final String ACTION_SET_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "action_set_selection_dialog_context";

    public static final String EDITOR_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "editor_selection_dialog_context";

    public static final String FILE_EXTENSION_DIALOG = PREFIX + //$NON-NLS-1$
    "file_extension_dialog_context";

    public static final String PREFERENCE_DIALOG = PREFIX + //$NON-NLS-1$
    "preference_dialog_context";

    public static final String PROBLEMS_VIEW = PREFIX + //$NON-NLS-1$
    "problem_view_context";

    public static final String PROPERTY_DIALOG = PREFIX + //$NON-NLS-1$
    "property_dialog_context";

    public static final String SAVE_PERSPECTIVE_DIALOG = PREFIX + //$NON-NLS-1$
    "save_perspective_dialog_context";

    public static final String SELECT_PERSPECTIVE_DIALOG = PREFIX + //$NON-NLS-1$
    "select_perspective_dialog_context";

    public static final String SHOW_VIEW_DIALOG = PREFIX + //$NON-NLS-1$
    "show_view_dialog_context";

    public static final String TYPE_FILTERING_DIALOG = PREFIX + //$NON-NLS-1$
    "type_filtering_dialog_context";

    public static final String LIST_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "list_selection_dialog_context";

    public static final String YES_NO_CANCEL_LIST_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "yes_no_cancel_list_selection_dialog_context";

    public static final String WORKING_SET_SELECTION_DIALOG = PREFIX + //$NON-NLS-1$
    "working_set_selection_dialog_context";

    public static final String WORKBENCH_EDITORS_DIALOG = PREFIX + //$NON-NLS-1$
    "workbench_editors_dialog";

    // // Editors
    public static final String FILE_EDITORS_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "file_editors_preference_page_context";

    public static final String PERSPECTIVES_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "perspectives_preference_page_context";

    public static final String VIEWS_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "views_preference_page_context";

    public static final String FONTS_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "font_preference_page_context";

    public static final String KEYS_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "keys_preference_page_context";

    public static final String WORKBENCH_EDITOR_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "workbench_editor_preference_page_context";

    public static final String CONTENT_TYPES_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "content_types_preference_page_context";

    public static final String WORKBENCH_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "workbench_preference_page_context";

    public static final String GLOBALIZATION_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "globalization_preference_page_context";

    public static final String DECORATORS_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "decorators_preference_page_context";

    public static final String STARTUP_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "startup_preference_page_context";

    public static final String WORKSPACES_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "workspaces_preference_page_context";

    //$NON-NLS-1$
    public static final String RESPONSIVE_UI = PREFIX + "responsive_ui_context";

    // // Windows
    public static final String DETACHED_WINDOW = PREFIX + //$NON-NLS-1$
    "detached_window_context";

    public static final String WORKBENCH_WINDOW = PREFIX + //$NON-NLS-1$
    "workbench_window_context";

    // // Wizard pages
    public static final String NEW_WIZARD_SELECTION_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "new_wizard_selection_wizard_page_context";

    public static final String EXPORT_WIZARD_SELECTION_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "export_wizard_selection_wizard_page_context";

    public static final String IMPORT_WIZARD_SELECTION_WIZARD_PAGE = PREFIX + //$NON-NLS-1$
    "import_wizard_selection_wizard_page_context";

    public static final String WORKING_SET_TYPE_PAGE = PREFIX + //$NON-NLS-1$
    "working_set_type_page";

    // Wizards
    //$NON-NLS-1$
    public static final String NEW_WIZARD = PREFIX + "new_wizard_context";

    public static final String NEW_WIZARD_SHORTCUT = PREFIX + //$NON-NLS-1$
    "new_wizard_shortcut_context";

    //$NON-NLS-1$
    public static final String IMPORT_WIZARD = PREFIX + "import_wizard_context";

    //$NON-NLS-1$
    public static final String EXPORT_WIZARD = PREFIX + "export_wizard_context";

    public static final String WORKING_SET_NEW_WIZARD = PREFIX + //$NON-NLS-1$
    "working_set_new_wizard_context";

    public static final String WORKING_SET_EDIT_WIZARD = PREFIX + //$NON-NLS-1$
    "working_set_edit_wizard_context";

    public static final String CAPABILITY_PREFERENCE_PAGE = PREFIX + //$NON-NLS-1$
    "capabilities_preference_page_context";

    public static final String TOGGLE_COOLBAR_ACTION = PREFIX + //$NON-NLS-1$
    "toggle_coolbar_action";
}

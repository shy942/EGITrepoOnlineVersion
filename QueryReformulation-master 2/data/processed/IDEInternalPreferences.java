/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.jface.dialogs.MessageDialogWithToggle;

/**
* The IDEInternalPreferences are the internal constants used by the Workbench.
*/
public interface IDEInternalPreferences {

    // (boolean) Save all dirty editors before running a full or incremental build
    //$NON-NLS-1$
    public static final String SAVE_ALL_BEFORE_BUILD = "SAVE_ALL_BEFORE_BUILD";

    // (boolean) Refresh workspace on startup
    //$NON-NLS-1$
    public static final String REFRESH_WORKSPACE_ON_STARTUP = "REFRESH_WORKSPACE_ON_STARTUP";

    // (int) Workspace save interval in minutes
    // @issue we should drop this and have clients refer to the core preference instead. its not even kept up-to-date if client uses core api directly
    //$NON-NLS-1$
    public static final String SAVE_INTERVAL = "saveInterval";

    public static final int MAX_SAVE_INTERVAL = 9999;

    // (boolean) Show Problems view to users when build contains errors
    //public static final String SHOW_TASKS_ON_BUILD = "SHOW_TASKS_ON_BUILD"; //$NON-NLS-1$
    // (boolean) Prompt for exit confirmation when last window closed.
    //$NON-NLS-1$
    public static final String EXIT_PROMPT_ON_CLOSE_LAST_WINDOW = "EXIT_PROMPT_ON_CLOSE_LAST_WINDOW";

    // (String) Whether to open the preferred perspective when creating a new project
    //$NON-NLS-1$
    public static final String PROJECT_SWITCH_PERSP_MODE = "SWITCH_PERSPECTIVE_ON_PROJECT_CREATION";

    /**
* (String) Whether to open required projects when opening a project.
*/
    //$NON-NLS-1$
    public static final String OPEN_REQUIRED_PROJECTS = "OPEN_REQUIRED_PROJECTS";

    /**
* (String) Whether to confirm closing unrelated projects.
*/
    //$NON-NLS-1$
    public static final String CLOSE_UNRELATED_PROJECTS = "CLOSE_UNRELATED_PROJECTS";

    public static final String PSPM_PROMPT = MessageDialogWithToggle.PROMPT;

    public static final String PSPM_ALWAYS = MessageDialogWithToggle.ALWAYS;

    public static final String PSPM_NEVER = MessageDialogWithToggle.NEVER;

    // (boolean) Whether or not to display the Welcome dialog on startup.
    //$NON-NLS-1$
    public static final String WELCOME_DIALOG = "WELCOME_DIALOG";

    //Whether or not to limit problems
    //$NON-NLS-1$
    public static final String LIMIT_PROBLEMS = "LIMIT_PROBLEMS";

    //The list of defined problems filters
    //$NON-NLS-1$
    public static final String PROBLEMS_FILTERS = "PROBLEMS_FILTERS";

    //problem limits
    //$NON-NLS-1$
    public static final String PROBLEMS_LIMIT = "PROBLEMS_LIMIT";

    //Whether or not to limit tasks
    //$NON-NLS-1$
    public static final String LIMIT_TASKS = "LIMIT_TASKS";

    //tasks limits
    //$NON-NLS-1$
    public static final String TASKS_LIMIT = "TASKS_LIMIT";

    //The list of defined tasks filters
    //$NON-NLS-1$
    public static final String TASKS_FILTERS = "TASKS_FILTERS";

    //Whether or not to limit bookmarks
    //$NON-NLS-1$
    public static final String LIMIT_BOOKMARKS = "LIMIT_BOOKMARKS";

    //bookmark limits
    //$NON-NLS-1$
    public static final String BOOKMARKS_LIMIT = "BOOKMARKS_LIMIT";

    //  The list of defined tasks filters
    //$NON-NLS-1$
    public static final String BOOKMARKS_FILTERS = "BOOKMARKS_FILTERS";

    //Enablement of marker limits
    //$NON-NLS-1$
    public static final String USE_MARKER_LIMITS = "USE_MARKER_LIMITS";

    //Value of marker limits
    //$NON-NLS-1$
    public static final String MARKER_LIMITS_VALUE = "MARKER_LIMITS_VALUE";

    // Type of import
    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_TYPE = "IMPORT_FILES_AND_FOLDERS_TYPE";

    // (boolean) Using variable relative paths for the import file and folder dialog
    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_RELATIVE = "IMPORT_FILES_AND_FOLDERS_RELATIVE";

    // (string) Save all dirty editors before running a full or incremental build
    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_MODE = "IMPORT_FILES_AND_FOLDERS_MODE";

    // (string) Save all dirty editors before running a full or incremental build
    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_VIRTUAL_FOLDER_MODE = "IMPORT_FILES_AND_FOLDERS_VIRTUAL_FOLDER_MODE";

    public static final String IMPORT_FILES_AND_FOLDERS_MODE_PROMPT = MessageDialogWithToggle.PROMPT;

    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_MODE_MOVE_COPY = "MOVE_COPY";

    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_MODE_LINK = "LINK";

    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_MODE_LINK_AND_VIRTUAL_FOLDER = "LINK_AND_VIRTUAL_FOLDER";

    // Always show this import window
    //$NON-NLS-1$
    public static final String IMPORT_FILES_AND_FOLDERS_SHOW_DIALOG = "IMPORT_FILES_AND_FOLDERS_SHOW_DIALOG";

    /**
* Workspace name, will be displayed in the window title.
*/
    //$NON-NLS-1$
    public static final String WORKSPACE_NAME = "WORKSPACE_NAME";

    /**
* Whether to show the (workspace) location in the window title.
*/
    //$NON-NLS-1$
    public static final String SHOW_LOCATION = "SHOW_LOCATION";

    /**
* System explore command, used to launch file manager showing selected
* resource.
*/
    //$NON-NLS-1$
    public static final String WORKBENCH_SYSTEM_EXPLORER = "SYSTEM_EXPLORER";

    /**
* Warn the user that the workspace is going to be upgraded because the IDE is newer
*/
    //$NON-NLS-1$
    public static final String WARN_ABOUT_WORKSPACE_INCOMPATIBILITY = "WARN_ABOUT_WORKSPACE_INCOMPATIBILITY";
}

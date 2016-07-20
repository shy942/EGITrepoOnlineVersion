/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.util.Util;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.handlers.ShowInSystemExplorerHandler;
import org.eclipse.ui.internal.views.markers.MarkerSupportInternalUtilities;

/**
* The IDEPreferenceInitializer is the preference initializer for the IDE
* preferences.
*/
public class IDEPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IEclipsePreferences node = DefaultScope.INSTANCE.getNode(IDEWorkbenchPlugin.getDefault().getBundle().getSymbolicName());
        // API preferences
        node.put(IDE.Preferences.PROJECT_OPEN_NEW_PERSPECTIVE, IWorkbenchPreferenceConstants.OPEN_PERSPECTIVE_REPLACE);
        // Set the workspace selection dialog to open by default
        node.putBoolean(IDE.Preferences.SHOW_WORKSPACE_SELECTION_DIALOG, true);
        // Internal preferences
        node.putBoolean(IDEInternalPreferences.SAVE_ALL_BEFORE_BUILD, false);
        // 5 minutes
        node.putInt(IDEInternalPreferences.SAVE_INTERVAL, 5);
        node.putBoolean(IDEInternalPreferences.WELCOME_DIALOG, true);
        node.putBoolean(IDEInternalPreferences.REFRESH_WORKSPACE_ON_STARTUP, false);
        node.putBoolean(IDEInternalPreferences.EXIT_PROMPT_ON_CLOSE_LAST_WINDOW, true);
        node.put(IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE, IDEInternalPreferences.PSPM_PROMPT);
        node.put(IDEInternalPreferences.OPEN_REQUIRED_PROJECTS, IDEInternalPreferences.PSPM_PROMPT);
        node.putBoolean(IDEInternalPreferences.CLOSE_UNRELATED_PROJECTS, false);
        node.putBoolean(IDEInternalPreferences.WARN_ABOUT_WORKSPACE_INCOMPATIBILITY, true);
        // Turning some Help Menu separators on
        //$NON-NLS-1$
        node.putBoolean(getHelpSeparatorKey("group.main"), true);
        //$NON-NLS-1$
        node.putBoolean(getHelpSeparatorKey("group.assist"), true);
        //$NON-NLS-1$
        node.putBoolean(getHelpSeparatorKey("group.updates"), true);
        // Set up marker limits
        node.putBoolean(IDEInternalPreferences.LIMIT_PROBLEMS, true);
        node.putInt(IDEInternalPreferences.PROBLEMS_LIMIT, 100);
        node.putBoolean(IDEInternalPreferences.LIMIT_BOOKMARKS, true);
        node.putInt(IDEInternalPreferences.BOOKMARKS_LIMIT, 100);
        node.putBoolean(IDEInternalPreferences.LIMIT_TASKS, true);
        node.putInt(IDEInternalPreferences.TASKS_LIMIT, 100);
        node.putBoolean(IDEInternalPreferences.USE_MARKER_LIMITS, true);
        node.putInt(IDEInternalPreferences.MARKER_LIMITS_VALUE, 100);
        //$NON-NLS-1$
        node.put(IDEInternalPreferences.IMPORT_FILES_AND_FOLDERS_TYPE, "");
        node.putBoolean(IDEInternalPreferences.IMPORT_FILES_AND_FOLDERS_RELATIVE, true);
        //Filter migration
        node.putBoolean(MarkerSupportInternalUtilities.MIGRATE_BOOKMARK_FILTERS, false);
        node.putBoolean(MarkerSupportInternalUtilities.MIGRATE_TASK_FILTERS, false);
        node.putBoolean(MarkerSupportInternalUtilities.MIGRATE_PROBLEM_FILTERS, false);
        node.put(IDEInternalPreferences.IMPORT_FILES_AND_FOLDERS_MODE, IDEInternalPreferences.IMPORT_FILES_AND_FOLDERS_MODE_PROMPT);
        node.put(IDEInternalPreferences.IMPORT_FILES_AND_FOLDERS_VIRTUAL_FOLDER_MODE, IDEInternalPreferences.IMPORT_FILES_AND_FOLDERS_MODE_PROMPT);
        node.put(IDEInternalPreferences.WORKBENCH_SYSTEM_EXPLORER, getShowInSystemExplorerCommand());
        node.put(IDE.UNASSOCIATED_EDITOR_STRATEGY_PREFERENCE_KEY, SystemEditorOrTextEditorStrategy.EXTENSION_ID);
    }

    /**
* The default command for launching the system explorer on this platform.
*
* @return The default command which launches the system explorer on this system, or an empty
*         string if no default exists
* @see ShowInSystemExplorerHandler#getDefaultCommand()
*/
    public static String getShowInSystemExplorerCommand() {
        // See https://bugs.eclipse.org/419940 why it is implemented in here and not in ShowInSystemExplorerHandler#getDefaultCommand()
        if (Util.isGtk()) {
            //$NON-NLS-1$
            return "dbus-send --print-reply --dest=org.freedesktop.FileManager1 /org/freedesktop/FileManager1 org.freedesktop.FileManager1.ShowItems array:string:\"${selected_resource_uri}\" string:\"\"";
        } else if (Util.isWindows()) {
            //$NON-NLS-1$
            return "explorer /E,/select=${selected_resource_loc}";
        } else if (Util.isMac()) {
            //$NON-NLS-1$
            return "open -R \"${selected_resource_loc}\"";
        }
        //$NON-NLS-1$
        return "";
    }

    private String getHelpSeparatorKey(String groupId) {
        //$NON-NLS-1$ //$NON-NLS-2$
        return "useSeparator." + IWorkbenchActionConstants.M_HELP + "." + groupId;
    }
}

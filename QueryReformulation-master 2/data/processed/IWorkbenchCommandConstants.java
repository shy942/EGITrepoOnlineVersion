/***/
package org.eclipse.ui;

/**
* Constants for all commands defined by the Eclipse workbench.
*
* @since 3.5
* @noextend This interface is not intended to be extended by clients.
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IWorkbenchCommandConstants {

    // File Category:
    /**
* Id for command "New" in category "File"
* (value is <code>"org.eclipse.ui.newWizard"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_NEW = "org.eclipse.ui.newWizard";

    /**
* Id for parameter "New Wizard" in command "New" in category "File" (value
* is <code>"newWizardId"</code>). Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String FILE_NEW_PARM_WIZARDID = "newWizardId";

    /**
* Id for command "Close" in category "File" (value is
* <code>"org.eclipse.ui.file.close"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_CLOSE = "org.eclipse.ui.file.close";

    /**
* Id for command "Close All" in category "File"
* (value is <code>"org.eclipse.ui.file.closeAll"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_CLOSE_ALL = "org.eclipse.ui.file.closeAll";

    /**
* Id for command "Import" in category "File"
* (value is <code>"org.eclipse.ui.file.import"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_IMPORT = "org.eclipse.ui.file.import";

    /**
* Id for parameter "Import Wizard" in command "Import" in category "File"
* (value is <code>"importWizardId"</code>). Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String FILE_IMPORT_PARM_WIZARDID = "importWizardId";

    /**
* Id for command "Export" in category "File" (value is
* <code>"org.eclipse.ui.file.export"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_EXPORT = "org.eclipse.ui.file.export";

    /**
* Id for parameter "Export Wizard" in command "Export" in category "File"
* (value is <code>"exportWizardId"</code>). Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String FILE_EXPORT_PARM_WIZARDID = "exportWizardId";

    /**
* Id for command "Save" in category "File"
* (value is <code>"org.eclipse.ui.file.save"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_SAVE = "org.eclipse.ui.file.save";

    /**
* Id for command "Save As" in category "File"
* (value is <code>"org.eclipse.ui.file.saveAs"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_SAVE_AS = "org.eclipse.ui.file.saveAs";

    /**
* Id for command "Save All" in category "File"
* (value is <code>"org.eclipse.ui.file.saveAll"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_SAVE_ALL = "org.eclipse.ui.file.saveAll";

    /**
* Id for command "Print" in category "File"
* (value is <code>"org.eclipse.ui.file.print"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_PRINT = "org.eclipse.ui.file.print";

    /**
* Id for command "Revert" in category "File"
* (value is <code>"org.eclipse.ui.file.revert"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_REVERT = "org.eclipse.ui.file.revert";

    /**
* Id for command "Restart" in category "File"
* (value is <code>"org.eclipse.ui.file.restartWorkbench"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_RESTART = "org.eclipse.ui.file.restartWorkbench";

    /**
* Id for command "Refresh" in category "File"
* (value is <code>"org.eclipse.ui.file.refresh"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_REFRESH = "org.eclipse.ui.file.refresh";

    /**
* Id for command "Properties" in category "File"
* (value is <code>"org.eclipse.ui.file.properties"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_PROPERTIES = "org.eclipse.ui.file.properties";

    /**
* Id for command "Exit" in category "File"
* (value is <code>"org.eclipse.ui.file.exit"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_EXIT = "org.eclipse.ui.file.exit";

    /**
* Id for command "Move" in category "File"
* (value is <code>"org.eclipse.ui.edit.move"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_MOVE = "org.eclipse.ui.edit.move";

    /**
* Id for command "Rename" in category "File"
* (value is <code>"org.eclipse.ui.edit.rename"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_RENAME = "org.eclipse.ui.edit.rename";

    /**
* Id for command "Close Others" in category "File"
* (value is <code>"org.eclipse.ui.file.closeOthers"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_CLOSE_OTHERS = "org.eclipse.ui.file.closeOthers";

    // Edit Category:
    /**
* Id for command "Undo" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.undo"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_UNDO = "org.eclipse.ui.edit.undo";

    /**
* Id for command "Redo" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.redo"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_REDO = "org.eclipse.ui.edit.redo";

    /**
* Id for command "Cut" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.cut"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_CUT = "org.eclipse.ui.edit.cut";

    /**
* Id for command "Copy" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.copy"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_COPY = "org.eclipse.ui.edit.copy";

    /**
* Id for command "Paste" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.paste"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_PASTE = "org.eclipse.ui.edit.paste";

    /**
* Id for command "Delete" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.delete"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_DELETE = "org.eclipse.ui.edit.delete";

    /**
* Id for command "Content Assist" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.text.contentAssist.proposals"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_CONTENT_ASSIST = "org.eclipse.ui.edit.text.contentAssist.proposals";

    /**
* Id for command "Context Information" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.text.contentAssist.contextInformation"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_CONTEXT_INFORMATION = "org.eclipse.ui.edit.text.contentAssist.contextInformation";

    /**
* Id for command "Select All" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.selectAll"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_SELECT_ALL = "org.eclipse.ui.edit.selectAll";

    /**
* Id for command "Find and Replace" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.findReplace"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_FIND_AND_REPLACE = "org.eclipse.ui.edit.findReplace";

    /**
* Id for command "Add Task" in category "Edit".
* (value is <code>"org.eclipse.ui.edit.addTask"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_ADD_TASK = "org.eclipse.ui.edit.addTask";

    /**
* Id for command "Add Bookmark" in category "Edit"
* (value is <code>"org.eclipse.ui.edit.addBookmark"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_ADD_BOOKMARK = "org.eclipse.ui.edit.addBookmark";

    // Navigate Category:
    /**
* Id for command "Go Into" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.goInto"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_GO_INTO = "org.eclipse.ui.navigate.goInto";

    /**
* Id for command "Back" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.back"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_BACK = "org.eclipse.ui.navigate.back";

    /**
* Id for command "Forward" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.forward"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_FORWARD = "org.eclipse.ui.navigate.forward";

    /**
* Id for command "Up" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.up"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_UP = "org.eclipse.ui.navigate.up";

    /**
* Id for command "Next" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.next"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_NEXT = "org.eclipse.ui.navigate.next";

    /**
* Id for command "Backward History" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.backwardHistory"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_BACKWARD_HISTORY = "org.eclipse.ui.navigate.backwardHistory";

    /**
* Id for command "Forward History" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.forwardHistory"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_FORWARD_HISTORY = "org.eclipse.ui.navigate.forwardHistory";

    /**
* Id for command "Previous" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.previous"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_PREVIOUS = "org.eclipse.ui.navigate.previous";

    /**
* Id for command "Toggle Link with Editor " in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.linkWithEditor"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_TOGGLE_LINK_WITH_EDITOR = "org.eclipse.ui.navigate.linkWithEditor";

    /**
* Id for command "Next Page" in category "Navigate"
* (value is <code>"org.eclipse.ui.part.nextPage"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_NEXT_PAGE = "org.eclipse.ui.part.nextPage";

    /**
* Id for command "Previous Page" in category "Navigate"
* (value is <code>"org.eclipse.ui.part.previousPage"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_PREVIOUS_PAGE = "org.eclipse.ui.part.previousPage";

    /**
* Id for command "Collapse All" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.collapseAll"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_COLLAPSE_ALL = "org.eclipse.ui.navigate.collapseAll";

    /**
* Id for command "Expand All" in category "Navigate" (value is
* <code>"org.eclipse.ui.navigate.expandAll"</code>).
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_EXPAND_ALL = "org.eclipse.ui.navigate.expandAll";

    /**
* Id for command "Show In" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.showIn"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_SHOW_IN = "org.eclipse.ui.navigate.showIn";

    /**
* Id for parameter "Target Id" in command "Show In" in category "Navigate"
* (value is <code>"org.eclipse.ui.navigate.showIn.targetId"</code>).
* Required.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_SHOW_IN_PARM_TARGET = "org.eclipse.ui.navigate.showIn.targetId";

    /**
* Id for command "Show In" in category "Navigate" (value is
* <code>"org.eclipse.ui.navigate.showInQuickMenu"</code>).
*/
    //$NON-NLS-1$
    public static final String NAVIGATE_SHOW_IN_QUICK_MENU = "org.eclipse.ui.navigate.showInQuickMenu";

    // project category
    /**
* Id for command "Build All" in category "Project".
* (value is <code>"org.eclipse.ui.project.buildAll"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJECT_BUILD_ALL = "org.eclipse.ui.project.buildAll";

    /**
* Id for command "Build Project" in category "Project".
* (value is <code>"org.eclipse.ui.project.buildProject"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJECT_BUILD_PROJECT = "org.eclipse.ui.project.buildProject";

    /**
* Id for command "Close Project" in category "Project".
* (value is <code>"org.eclipse.ui.project.closeProject"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJECT_CLOSE_PROJECT = "org.eclipse.ui.project.closeProject";

    /**
* Id for command "Close Unrelated Projects" in category "Project".
* (value is <code>"org.eclipse.ui.project.closeUnrelatedProjects"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJECT_CLOSE_UNRELATED_PROJECTS = "org.eclipse.ui.project.closeUnrelatedProjects";

    /**
* Id for command "Open Project" in category "Project".
* (value is <code>"org.eclipse.ui.project.openProject"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJECT_OPEN_PROJECT = "org.eclipse.ui.project.openProject";

    // Window Category:
    /**
* Id for command "New Window" in category "Window"
* (value is <code>"org.eclipse.ui.window.newWindow"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_NEW_WINDOW = "org.eclipse.ui.window.newWindow";

    /**
* Id for command "New Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.newEditor"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_NEW_EDITOR = "org.eclipse.ui.window.newEditor";

    /**
* Id for command "Show View Menu" in category "Window"
* (value is <code>"org.eclipse.ui.window.showViewMenu"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_SHOW_VIEW_MENU = "org.eclipse.ui.window.showViewMenu";

    /**
* Id for command "Activate Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.activateEditor"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_ACTIVATE_EDITOR = "org.eclipse.ui.window.activateEditor";

    /**
* Id for command "Maximize Active View or Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.maximizePart"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_MAXIMIZE_ACTIVE_VIEW_OR_EDITOR = "org.eclipse.ui.window.maximizePart";

    /**
* Id for command "Minimize Active View or Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.minimizePart"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_MINIMIZE_ACTIVE_VIEW_OR_EDITOR = "org.eclipse.ui.window.minimizePart";

    /**
* Id for command "Next Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.nextEditor"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_NEXT_EDITOR = "org.eclipse.ui.window.nextEditor";

    /**
* Id for command "Previous Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.previousEditor"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_PREVIOUS_EDITOR = "org.eclipse.ui.window.previousEditor";

    /**
* Id for command "Next View" in category "Window"
* (value is <code>"org.eclipse.ui.window.nextView"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_NEXT_VIEW = "org.eclipse.ui.window.nextView";

    /**
* Id for command "Previous View" in category "Window"
* (value is <code>"org.eclipse.ui.window.previousView"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_PREVIOUS_VIEW = "org.eclipse.ui.window.previousView";

    /**
* Id for command "Next Perspective" in category "Window"
* (value is <code>"org.eclipse.ui.window.nextPerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_NEXT_PERSPECTIVE = "org.eclipse.ui.window.nextPerspective";

    /**
* Id for command "Previous Perspective" in category "Window"
* (value is <code>"org.eclipse.ui.window.previousPerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_PREVIOUS_PERSPECTIVE = "org.eclipse.ui.window.previousPerspective";

    /**
* Id for command "Close All Perspectives" in category "Window"
* (value is <code>"org.eclipse.ui.window.closeAllPerspectives"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_CLOSE_ALL_PERSPECTIVES = "org.eclipse.ui.window.closeAllPerspectives";

    /**
* Id for command "Close Perspective" in category "Window"
* (value is <code>"org.eclipse.ui.window.closePerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_CLOSE_PERSPECTIVE = "org.eclipse.ui.window.closePerspective";

    /**
* Id for parameter "Perspective Id" in command "Close Perspective" in
* category "Window" (value is
* <code>"org.eclipse.ui.window.closePerspective.perspectiveId"</code>).
* Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String WINDOW_CLOSE_PERSPECTIVE_PARM_ID = "org.eclipse.ui.window.closePerspective.perspectiveId";

    /**
* Id for command "Close Part" in category "Window" (value is
* <code>"org.eclipse.ui.file.closePart"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_CLOSE_PART = "org.eclipse.ui.file.closePart";

    /**
* Id for command "Customize Perspective" in category "Window"
* (value is <code>"org.eclipse.ui.window.customizePerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_CUSTOMIZE_PERSPECTIVE = "org.eclipse.ui.window.customizePerspective";

    /**
* Id for command "Pin Editor" in category "Window"
* (value is <code>"org.eclipse.ui.window.pinEditor"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_PIN_EDITOR = "org.eclipse.ui.window.pinEditor";

    /**
* Id for command "Preferences" in category "Window"
* (value is <code>"org.eclipse.ui.window.preferences"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_PREFERENCES = "org.eclipse.ui.window.preferences";

    /**
* Id for parameter "Preference Page Id" in command "Preferences" in
* category "Window" (value is <code>"preferencePageId"</code>). Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String WINDOW_PREFERENCES_PARM_PAGEID = "preferencePageId";

    /**
* Id for command "Reset Perspective" in category "Window" (value is
* <code>"org.eclipse.ui.window.resetPerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_RESET_PERSPECTIVE = "org.eclipse.ui.window.resetPerspective";

    /**
* Id for command "Save Perspective As" in category "Window"
* (value is <code>"org.eclipse.ui.window.savePerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_SAVE_PERSPECTIVE_AS = "org.eclipse.ui.window.savePerspective";

    /**
* Id for command "Show Key Assist" in category "Window"
* (value is <code>"org.eclipse.ui.window.showKeyAssist"</code>).
*/
    //$NON-NLS-1$
    public static final String WINDOW_SHOW_KEY_ASSIST = "org.eclipse.ui.window.showKeyAssist";

    /**
* Id for command "Lock Toolbar" in category "Window" (value is
* <code>"org.eclipse.ui.window.lockToolbar"</code>).
*
* @since 3.7
*/
    //$NON-NLS-1$
    public static final String WINDOW_LOCK_TOOLBAR = "org.eclipse.ui.window.lockToolBar";

    // Help Category:
    /**
* Id for command "Help Contents" in category "Help"
* (value is <code>"org.eclipse.ui.help.helpContents"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_HELP_CONTENTS = "org.eclipse.ui.help.helpContents";

    /**
* Id for command "Help Search" in category "Help"
* (value is <code>"org.eclipse.ui.help.helpSearch"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_HELP_SEARCH = "org.eclipse.ui.help.helpSearch";

    /**
* Id for command "Dynamic Help" in category "Help"
* (value is <code>"org.eclipse.ui.help.dynamicHelp"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_DYNAMIC_HELP = "org.eclipse.ui.help.dynamicHelp";

    /**
* Id for command "Welcome" in category "Help"
* (value is <code>"org.eclipse.ui.help.quickStartAction"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_WELCOME = "org.eclipse.ui.help.quickStartAction";

    /**
* Id for command "Tips and Tricks" in category "Help"
* (value is <code>"org.eclipse.ui.help.tipsAndTricksAction"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_TIPS_AND_TRICKS = "org.eclipse.ui.help.tipsAndTricksAction";

    /**
* Id for command "About" in category "Help"
* (value is <code>"org.eclipse.ui.help.aboutAction"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_ABOUT = "org.eclipse.ui.help.aboutAction";

    // Views Category:
    /**
* Id for command "Show View" in category "Views"
* (value is <code>"org.eclipse.ui.views.showView"</code>).
*/
    //$NON-NLS-1$
    public static final String VIEWS_SHOW_VIEW = "org.eclipse.ui.views.showView";

    /**
* Id for parameter "View Id" in command "Show View" in category "Views"
* (value is <code>"org.eclipse.ui.views.showView.viewId"</code>).
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String VIEWS_SHOW_VIEW_PARM_ID = "org.eclipse.ui.views.showView.viewId";

    /**
* Id for parameter "Secondary Id" in command "Show View" in category
* "Views" (value is
* <code>"org.eclipse.ui.views.showView.secondaryId"</code>).
*
* @since 3.7
*/
    //$NON-NLS-1$
    public static final String VIEWS_SHOW_VIEW_SECONDARY_ID = "org.eclipse.ui.views.showView.secondaryId";

    /**
* Id for parameter "As Fastview" in command "Show View" in category "Views"
* (value is <code>"org.eclipse.ui.views.showView.makeFast"</code>).
* Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String VIEWS_SHOW_VIEW_PARM_FASTVIEW = "org.eclipse.ui.views.showView.makeFast";

    // Perspectives Category:
    /**
* Id for command "Show Perspective" in category "Perspectives"
* (value is <code>"org.eclipse.ui.perspectives.showPerspective"</code>).
*/
    //$NON-NLS-1$
    public static final String PERSPECTIVES_SHOW_PERSPECTIVE = "org.eclipse.ui.perspectives.showPerspective";

    /**
* Id for parameter "Perspective Id" in command "Show Perspective" in
* category "Perspectives" (value is
* <code>"org.eclipse.ui.perspectives.showPerspective.perspectiveId"</code>
* ).
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String PERSPECTIVES_SHOW_PERSPECTIVE_PARM_ID = "org.eclipse.ui.perspectives.showPerspective.perspectiveId";

    /**
* Id for parameter "In New Window" in command "Show Perspective" in
* category "Perspectives" (value is
* <code>"org.eclipse.ui.perspectives.showPerspective.newWindow"</code>).
* Optional.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String PERSPECTIVES_SHOW_PERSPECTIVE_PARM_NEWWINDOW = "org.eclipse.ui.perspectives.showPerspective.newWindow";
}

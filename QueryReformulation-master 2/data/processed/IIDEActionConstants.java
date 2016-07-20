/***/
package org.eclipse.ui.ide;

/**
* Identifiers for IDE menus, toolbars and groups.
* <p>
* This interface contains constants only; it is not intended to be implemented
* or extended.
* </p>
*
* Note: want to move IDE-specific stuff out of IWorkbenchActionConstants.
*   There's still some cleanup to be done here (and there).
*
* @since 3.0
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IIDEActionConstants {

    /**
* Name of standard File menu (value <code>"file"</code>).
*/
    //$NON-NLS-1$
    public static final String M_FILE = "file";

    /**
* Name of standard Edit menu (value <code>"edit"</code>).
*/
    //$NON-NLS-1$
    public static final String M_EDIT = "edit";

    /**
* Name of standard Navigate menu (value <code>"navigate"</code>).
*/
    //$NON-NLS-1$
    public static final String M_NAVIGATE = "navigate";

    /**
* Name of standard Project menu (value <code>"project"</code>).
*/
    //$NON-NLS-1$
    public static final String M_PROJECT = "project";

    /**
* Name of standard Window menu (value <code>"window"</code>).
*/
    //$NON-NLS-1$
    public static final String M_WINDOW = "window";

    /**
* Name of standard Help menu (value <code>"help"</code>).
*/
    //$NON-NLS-1$
    public static final String M_HELP = "help";

    /**
* File menu: name of group for start of menu (value <code>"fileStart"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_START = "fileStart";

    /**
* File menu: name of group for end of menu (value <code>"fileEnd"</code>).
*/
    //$NON-NLS-1$
    public static final String FILE_END = "fileEnd";

    /**
* File menu: name of group for extra New-like actions (value <code>"new.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String NEW_EXT = "new.ext";

    /**
* File menu: name of group for extra Close-like actions (value <code>"close.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String CLOSE_EXT = "close.ext";

    /**
* File menu: name of group for extra Save-like actions (value <code>"save.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String SAVE_EXT = "save.ext";

    /**
* File menu: name of group for extra Print-like actions (value <code>"print.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String PRINT_EXT = "print.ext";

    /**
* File menu: name of group for extra Import-like actions (value <code>"import.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String IMPORT_EXT = "import.ext";

    /**
* File menu: name of "Most Recently Used File" group.
* (value <code>"mru"</code>).
*/
    //$NON-NLS-1$
    public static final String MRU = "mru";

    /**
* Edit menu: name of group for start of menu (value <code>"editStart"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_START = "editStart";

    /**
* Edit menu: name of group for end of menu (value <code>"editEnd"</code>).
*/
    //$NON-NLS-1$
    public static final String EDIT_END = "editEnd";

    /**
* Edit menu: name of group for extra Undo-like actions (value <code>"undo.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String UNDO_EXT = "undo.ext";

    /**
* Edit menu: name of group for extra Cut-like actions (value <code>"cut.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String CUT_EXT = "cut.ext";

    /**
* Edit menu: name of group for extra Find-like actions (value <code>"find.ext"</code>).
* <p>Note: The value of this constant has changed in 3.3 to match the specification;
* before 3.3, its value was incorrect (<code>"cut.ext"</code>).  See bug 155856 for details.</p>
*/
    //$NON-NLS-1$
    public static final String FIND_EXT = "find.ext";

    /**
* Edit menu: name of group for extra Add-like actions (value <code>"add.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String ADD_EXT = "add.ext";

    /**
* Workbench menu: name of group for extra Build-like actions
* (value <code>"build.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String BUILD_EXT = "build.ext";

    /**
* Workbench toolbar id for file toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String TOOLBAR_FILE = "org.eclipse.ui.workbench.file";

    /**
* Workbench toolbar id for navigate toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String TOOLBAR_NAVIGATE = "org.eclipse.ui.workbench.navigate";

    // Workbench toolbar group ids.  To add an item at the beginning of the group,
    // use the GROUP id.  To add an item at the end of the group, use the EXT id.
    /**
* Group id for pin toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String PIN_GROUP = "pin.group";

    /**
* Group ids for history toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String HISTORY_GROUP = "history.group";

    /**
* Group ids for new toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String NEW_GROUP = "new.group";

    /**
* Group ids for save toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String SAVE_GROUP = "save.group";

    /**
* Group ids for build toolbar group.
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String BUILD_GROUP = "build.group";

    // Pop-up menu groups:
    /**
* Pop-up menu: name of group for Add actions (value <code>"group.add"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_ADD = "group.add";

    /**
* Pop-up menu and cool bar: name of group for File actions (value <code>"group.file"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_FILE = "group.file";

    /**
* Coolbar: name of group for Navigate actions (value <code>"group.nav"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_NAV = "group.nav";

    /**
* Pop-up menu: name of group for Show In actions (value <code>"group.showIn"</code>).
*
* @since 2.1
*/
    //$NON-NLS-1$
    public static final String GROUP_SHOW_IN = "group.showIn";

    /**
* Navigate menu: name of group for start of menu
* (value <code>"navStart"</code>).
*/
    //$NON-NLS-1$
    public static final String NAV_START = "navStart";

    /**
* Navigate menu: name of group for end of menu
* (value <code>"navEnd"</code>).
*/
    //$NON-NLS-1$
    public static final String NAV_END = "navEnd";

    /**
* Navigate menu: name of group for extra Open actions
* (value <code>"open.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String OPEN_EXT = "open.ext";

    /**
* Navigate menu: name of group for extra Show actions
* (value <code>"show.ext"</code>).
*/
    //$NON-NLS-1$
    public static final String SHOW_EXT = "show.ext";

    /**
* Navigate menu: name of standard Go Into global action
* (value <code>"goInto"</code>).
*/
    // Global action. //$NON-NLS-1$
    public static final String GO_INTO = "goInto";

    /**
* Navigate menu: name of standard Go To submenu
* (value <code>"goTo"</code>).
*/
    //$NON-NLS-1$
    public static final String GO_TO = "goTo";

    /**
* Navigate menu: name of standard Go To Resource global action
* (value <code>"goToResource"</code>).
*
* Note:should be in an action factory
*/
    // Global action. //$NON-NLS-1$
    public static final String GO_TO_RESOURCE = "goToResource";

    /**
* Project menu: name of group for start of menu
* (value <code>"projStart"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJ_START = "projStart";

    /**
* Project menu: name of group for start of menu
* (value <code>"projEnd"</code>).
*/
    //$NON-NLS-1$
    public static final String PROJ_END = "projEnd";

    /**
* Help menu: name of group for start of menu
* (value <code>"helpStart"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_START = "helpStart";

    /**
* Help menu: name of group for end of menu
* (value <code>"helpEnd"</code>).
*/
    //$NON-NLS-1$
    public static final String HELP_END = "helpEnd";
}

/***/
package org.eclipse.ui.internal;

/**
* The IPreferenceConstants are the internal constants used by the Workbench.
*/
public interface IPreferenceConstants {

    //Boolean: true = single click opens editor; false = double click opens
    // it.
    //$NON-NLS-1$
    public static final String OPEN_ON_SINGLE_CLICK = "OPEN_ON_SINGLE_CLICK";

    //Boolean: true = select on hover;
    //$NON-NLS-1$
    public static final String SELECT_ON_HOVER = "SELECT_ON_HOVER";

    //Boolean: true = open after delay
    //$NON-NLS-1$
    public static final String OPEN_AFTER_DELAY = "OPEN_AFTER_DELAY";

    // Boolean: true = bidi support enabled; false = bidi support disabled
    //$NON-NLS-1$
    public static final String BIDI_SUPPORT = "BIDI_SUPPORT";

    // String: Text direction. May have the following values: "ltr", "rtl",
    // "auto", and "".
    //$NON-NLS-1$
    public static final String TEXT_DIRECTION = "TEXT_DIRECTION";

    // String: Layout direction. May have the following values:
    // SWT.LEFT_TO_RIGHT, SWT.RIGHT_TO_LEFT, and SWT.NONE.
    //$NON-NLS-1$
    public static final String LAYOUT_DIRECTION = "LAYOUT_DIRECTION";

    // String: Unicode locale extensions
    //$NON-NLS-1$
    public static final String NL_EXTENSIONS = "NL_EXTENSIONS";

    //Do we show color icons in toolbars?
    //$NON-NLS-1$
    public static final String COLOR_ICONS = "COLOR_ICONS";

    //mappings for type/extension to an editor
    //$NON-NLS-1$
    public static final String EDITORS = "editors";

    //$NON-NLS-1$
    public static final String RESOURCES = "resourcetypes";

    //saving perspective layouts
    //$NON-NLS-1$
    public static final String PERSPECTIVES = "perspectives";

    // (int) If > 0, an editor will be reused once 'N' editors are opened.
    //$NON-NLS-1$
    public static final String REUSE_EDITORS = "REUSE_OPEN_EDITORS";

    //Boolean: true = replace dirty editor if no other editors to reuse
    // (prompt for save);
    //			false = open a new editor if no other editors to resuse
    //$NON-NLS-1$
    public static final String REUSE_DIRTY_EDITORS = "REUSE_DIRTY_EDITORS";

    //On/Off option for the two preceding options.
    //$NON-NLS-1$
    public static final String REUSE_EDITORS_BOOLEAN = "REUSE_OPEN_EDITORS_BOOLEAN";

    // (int) N recently viewed files will be listed in the File->Open Recent
    // menu.
    //$NON-NLS-1$
    public static final String RECENT_FILES = "RECENT_FILES";

    // (int) Mode for opening a new perspective
    //$NON-NLS-1$
    public static final String OPEN_PERSP_MODE = "OPEN_PERSPECTIVE_MODE";

    public static final int OPM_ACTIVE_PAGE = 0;

    //public static final int OPM_NEW_PAGE = 1;
    public static final int OPM_NEW_WINDOW = 2;

    //Identifier for enabled decorators
    //$NON-NLS-1$
    public static final String ENABLED_DECORATORS = "ENABLED_DECORATORS";

    //Boolean: true = keep cycle part dialog open when keys released
    //$NON-NLS-1$
    public static final String STICKY_CYCLE = "STICKY_CYCLE";

    //List of plugins but that extends "startup" extension point but are
    // overriden by the user.
    //String of plugin unique ids separated by ";"
    //$NON-NLS-1$
    public static final String PLUGINS_NOT_ACTIVATED_ON_STARTUP = "PLUGINS_NOT_ACTIVATED_ON_STARTUP";

    //Separator for PLUGINS_NOT_ACTIVATED_ON_STARTUP
    public static char SEPARATOR = ';';

    //Preference key for default editors
    //$NON-NLS-1$
    public static final String DEFAULT_EDITORS = "defaultEditors";

    //Preference key for default editors
    //$NON-NLS-1$
    public static final String DEFAULT_EDITORS_CACHE = "defaultEditorsCache";

    //Tab width = tab height * scalar value
    //$NON-NLS-1$
    public static final String EDITOR_TAB_WIDTH = "EDITOR_TAB_WIDTH";

    //Boolean: true = show Editors drop down button on CTabFolder
    //$NON-NLS-1$
    public static final String EDITORLIST_PULLDOWN_ACTIVE = "EDITORLIST_PULLDOWN_ACTIVE";

    // Selection scope for EditorList
    //$NON-NLS-1$
    public static final String EDITORLIST_SELECTION_SCOPE = "EDITORLIST_SELECTION_SCOPE";

    public static final int EDITORLIST_SET_WINDOW_SCOPE = 0;

    public static final int EDITORLIST_SET_PAGE_SCOPE = 1;

    public static final int EDITORLIST_SET_TAB_GROUP_SCOPE = 2;

    // Sort criteria for EditorList
    //$NON-NLS-1$
    public static final String EDITORLIST_SORT_CRITERIA = "EDITORLIST_SORT_CRITERIA";

    public static final int EDITORLIST_NAME_SORT = 0;

    public static final int EDITORLIST_MRU_SORT = 1;

    /**
* Boolean; true = EditorList displays full path
*/
    //$NON-NLS-1$
    public static final String EDITORLIST_DISPLAY_FULL_NAME = "EDITORLIST_DISPLAY_FULL_NAME";

    /**
* Workbench preference id for determining whether the user has chosen to
* override some of the settings in the current presentation.
* <p>
* The default value for this preference is: <code>false</code> (prompt)
* </p>
*
* @since 3.2
*/
    //$//$NON-NLS-1$
    public static final String OVERRIDE_PRESENTATION = "overridepresentation";

    /**
* <p>
* The key for the preference indicating which tab is selected in the keys
* preference page when last okay was pressed. This value should never
* really be directly edited by a user.
* </p>
* <p>
* This preference is an <code>int</code> value. The default value is
* <code>0</code>.
* </p>
*
* @since 3.1
*/
    //$NON-NLS-1$
    public static final String KEYS_PREFERENCE_SELECTED_TAB = "KEYS_PREFERENCE_SELECTED_TAB";

    /**
* <p>
* The key for the preference indicating whether multi-stroke key sequences
* should provide assistance to the user. This means that if the user pauses
* after pressing the first key, a window will open showing the possible
* completions.
* </p>
* <p>
* This preference is a <code>boolean</code> value. The default value is
* <code>false</code>.
* </p>
*
* @since 3.0
*/
    //$NON-NLS-1$
    public static final String MULTI_KEY_ASSIST = "MULTI_KEY_ASSIST";

    /**
* <p>
* The key for the preference indicating how long the assist window should
* wait before opening. This is a value in milliseconds -- from the time the
* first key in a multi-key is received by the system, to the time the
* assist window should appear.
* </p>
* <p>
* This preference is an <code>int</code> value. The default value is
* <code>1000</code>.
* </p>
*
* @since 3.0
*/
    //$NON-NLS-1$
    public static final String MULTI_KEY_ASSIST_TIME = "MULTI_KEY_ASSIST_TIME";

    /**
* Workbench preference to use the new IPersistableEditor interface
* throughout the workbench new editor/open editor calls.
*
* @since 3.3
*/
    //$NON-NLS-1$
    public static String USE_IPERSISTABLE_EDITORS = "USE_IPERSISTABLE_EDITORS";

    /**
* Preference to show user jobs in a dialog.
*/
    //$NON-NLS-1$
    public static String RUN_IN_BACKGROUND = "RUN_IN_BACKGROUND";

    /**
* Workbench preference id for determining whether the user will be prompted
* for activity enablement. If this is false then activities are enabled
* automatically. If it is true, then the user is only prompted for
* activities that they have not already declared a disinterest in via the
* prompt dialog.
* <p>
* The default value for this preference is: <code>true</code> (prompt)
* </p>
*
* @since 3.0
*/
    //$NON-NLS-1$
    public static final String SHOULD_PROMPT_FOR_ENABLEMENT = "shouldPromptForEnablement";

    /**
* Preference to show/hide the CoolBar.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String COOLBAR_VISIBLE = "coolBarVisible";

    /**
* Preference to show/hide the PerspectiveBar.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String PERSPECTIVEBAR_VISIBLE = "perspectiveBarVisible";

    /**
* Preference that restores the 3.2 startup threading behavior. This
* essentially means that there will be no restrictions on what runnables
* will be processed via the UI synchronizer.
*
* <p>
* This preference will likely disappear in 3.5 in favor of a proper
* solution to bug 219913.
* </p>
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String USE_32_THREADING = "use32Threading";

    /**
* Preference value that specifies the time interval in minutes between
* workbench auto-saves. If the value is zero it disables workbench
* auto-save.
*
* @since 3.105
*/
    //$NON-NLS-1$
    public static final String WORKBENCH_SAVE_INTERVAL = "WORKBENCH_SAVE_INTERVAL";

    /**
* This preference is the threshold value to determine whether a document is
* large or not. When the user tries to open a file larger than the
* threshold, then EditorSelectionDialog will be opened, suggesting the user
* to open with an external editor.
* <p>
* This preference is a <code>long</code> value that represents the
* threshold in bytes. The default value is <code>0</code> meaning no
* prompting on editor opening.
* </p>
*
* @since 3.7
*/
    //$NON-NLS-1$
    public static final String LARGE_DOC_SIZE_FOR_EDITORS = "LARGE_DOC_SIZE_FOR_EDITORS";

    /**
* Preference id for whether the editors may save automatically.
* <p>
* The boolean default value for this preference is: <code>false</code>.
* </p>
*
* @since 3.8
*/
    //$NON-NLS-1$
    public static final String SAVE_AUTOMATICALLY = "SAVE_AUTOMATICALLY";

    /**
* Preference value that specifies the time interval in seconds between
* editors auto-saves.
* <p>
* The integer default value for this preference is: <code>60</code>.
* </p>
*
* @since 3.8
*/
    //$NON-NLS-1$
    public static final String SAVE_AUTOMATICALLY_INTERVAL = "SAVE_AUTOMATICALLY_INTERVAL";
}

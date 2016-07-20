/***/
package org.eclipse.ui.menus;

/**
* Provides utilities and constants for use with the new menus API.
*
* @since 3.3
* @noextend This class is not intended to be subclassed by clients.
* @noinstantiate This class is not intended to be instantiated by clients.
*/
public class MenuUtil {

    /**
* Workbench Menu. On supported platforms, this menu is shown when no
* workbench windows are active
*
* @since 3.7
*/
    //$NON-NLS-1$
    public static final String WORKBENCH_MENU = "menu:org.eclipse.ui.workbench.menu";

    /** Main Menu */
    //$NON-NLS-1$
    public static final String MAIN_MENU = "menu:org.eclipse.ui.main.menu";

    /** Main ToolBar (CoolBar) */
    //$NON-NLS-1$
    public static final String MAIN_TOOLBAR = "toolbar:org.eclipse.ui.main.toolbar";

    /** -Any- Popup Menu */
    //$NON-NLS-1$
    public static final String ANY_POPUP = "popup:org.eclipse.ui.popup.any";

    /** Top Left Trim Area */
    //$NON-NLS-1$
    public static final String TRIM_COMMAND1 = "toolbar:org.eclipse.ui.trim.command1";

    /** Top Right Trim Area */
    //$NON-NLS-1$
    public static final String TRIM_COMMAND2 = "toolbar:org.eclipse.ui.trim.command2";

    /** Left Vertical Trim Area */
    //$NON-NLS-1$
    public static final String TRIM_VERTICAL1 = "toolbar:org.eclipse.ui.trim.vertical1";

    /** Right Vertical Trim Area */
    //$NON-NLS-1$
    public static final String TRIM_VERTICAL2 = "toolbar:org.eclipse.ui.trim.vertical2";

    /** Bottom (Status) Trim Area */
    //$NON-NLS-1$
    public static final String TRIM_STATUS = "toolbar:org.eclipse.ui.trim.status";

    /**
* Valid query attribute. Usage <b>menu:menu.id?before=contribution.id</b>.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String QUERY_BEFORE = "before";

    /**
* Valid query attribute. Usage <b>menu:menu.id?after=contribution.id</b>.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String QUERY_AFTER = "after";

    /**
* Valid query attribute. Usage <b>menu:menu.id?endof=contribution.id</b>.
* <p>
* This menu contribution will be placed at the end of the group defined by
* <b>contribution.id</b> (usually right in front of the next group marker
* or separator). Further contribution processing can still place other
* contributions after this one.
* </p>
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String QUERY_ENDOF = "endof";

    /**
* Contributions of targets to this location will be included with the show
* in menu.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String SHOW_IN_MENU_ID = "popup:org.eclipse.ui.menus.showInMenu";

    /**
* @param id
*            The menu's id
* @return The locator URI for a menu with the given id
*/
    public static String menuUri(String id) {
        //$NON-NLS-1$
        return "menu:" + id;
    }

    /**
* @param id
*            The id of the menu
* @param location
*            The relative location specifier
* @param refId
*            The id of the menu element to be relative to
* @return A location URI formatted with the given parameters
*/
    public static String menuAddition(String id, String location, String refId) {
        return menuUri(id) + '?' + location + '=' + refId;
    }

    /**
* Convenience method to create a standard menu addition The resulting
* string has the format: "menu:[id]?after=additions"
*
* @param id
*            The id of the root element to contribute to
* @return The formatted string
*/
    public static String menuAddition(String id) {
        //$NON-NLS-1$//$NON-NLS-2$
        return menuAddition(id, "after", "additions");
    }

    /**
* @param id
*            The toolbar's id
* @return The lcoation URI for a toolbar with the given id
*/
    public static String toolbarUri(String id) {
        //$NON-NLS-1$
        return "toolbar:" + id;
    }

    /**
* @param id
*            The id of the toolbar
* @param location
*            The relative location specifier
* @param refId
*            The id of the toolbar element to be relative to
* @return A location URI formatted with the given parameters
*/
    public static String toolbarAddition(String id, String location, String refId) {
        return toolbarUri(id) + '?' + location + '=' + refId;
    }

    /**
* Convenience method to create a standard toolbar addition The resulting
* string has the format: "toolbar:[id]?after=additions"
*
* @param id
*            The id of the root element to contribute to
* @return The formatted string
*/
    public static String toolbarAddition(String id) {
        //$NON-NLS-1$//$NON-NLS-2$
        return toolbarAddition(id, "after", "additions");
    }
}

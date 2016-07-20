/***/
package org.eclipse.ui.internal.quickaccess;

import org.eclipse.osgi.util.NLS;

/**
* @since 3.2
*
*/
public class QuickAccessMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.quickaccess.messages";

    public static String QuickAccess_TooltipDescription;

    public static String QuickAccess_TooltipDescription_Empty;

    public static String QuickAccess_Perspectives;

    public static String QuickAccess_Commands;

    public static String QuickAccess_Properties;

    public static String QuickAccess_Editors;

    public static String QuickAccess_Menus;

    public static String QuickAccess_New;

    public static String QuickAccess_Preferences;

    public static String QuickAccess_Previous;

    public static String QuickAccess_Views;

    public static String QuickAccess_PressKeyToShowAllMatches;

    public static String QuickAccess_StartTypingToFindMatches;

    public static String QuickAccess_AvailableCategories;

    public static String QuickAccess_EnterSearch;

    public static String QuickAccess_SelectedString;

    public static String QuickAccess_ViewWithCategory;

    public static String QuickAccessContents_NoMatchingResults;

    public static String QuickAccessContents_PressKeyToLimitResults;

    public static String QuickAccessContents_QuickAccess;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, QuickAccessMessages.class);
    }

    private  QuickAccessMessages() {
    }
}

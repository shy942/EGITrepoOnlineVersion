/***/
package org.eclipse.ui.internal.activities.ws;

import org.eclipse.osgi.util.NLS;

/**
* The ActivtyMessages are the messages used by the activities
* support.
*
*/
public class ActivityMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.activities.ws.messages";

    public static String ActivityEnabler_description;

    public static String ActivityEnabler_activities;

    public static String ActivityEnabler_categories;

    public static String ActivityEnabler_selectAll;

    public static String ActivityEnabler_deselectAll;

    public static String ActivitiesPreferencePage_advancedDialogTitle;

    public static String ActivitiesPreferencePage_advancedButton;

    public static String ActivitiesPreferencePage_lockedMessage;

    public static String ActivitiesPreferencePage_captionMessage;

    public static String ActivitiesPreferencePage_requirements;

    public static String ManagerTask;

    public static String ManagerWindowSubTask;

    public static String ManagerViewsSubTask;

    public static String Perspective_showAll;

    public static String activityPromptButton;

    public static String activityPromptToolTip;

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, ActivityMessages.class);
    }
}

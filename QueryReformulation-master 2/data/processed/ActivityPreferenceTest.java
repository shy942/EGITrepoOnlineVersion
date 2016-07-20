/***/
package org.eclipse.ui.tests.activities;

import java.util.Collections;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* @since 3.1
*/
public class ActivityPreferenceTest extends UITestCase {

    /**
* Preference prefix - must match the one specified in ActivityPreferenceHelper
*/
    //$NON-NLS-1$
    private static String PREFIX = "UIActivities.";

    /**
* The activity id
*/
    //$NON-NLS-1$
    private static String ID = "org.eclipse.ui.PT.A2";

    /**
* @param testName
*/
    public  ActivityPreferenceTest(String testName) {
        super(testName);
    }

    /**
* Tests whether activity preferences are persisted as soon as the activity set changes.
*/
    public void testActivityPreference() {
        IActivityManager manager = fWorkbench.getActivitySupport().getActivityManager();
        boolean initialState = manager.getEnabledActivityIds().contains(ID);
        IPreferenceStore store = WorkbenchPlugin.getDefault().getPreferenceStore();
        assertEquals(initialState, store.getBoolean(PREFIX + ID));
        fWorkbench.getActivitySupport().setEnabledActivityIds(initialState ? Collections.EMPTY_SET : Collections.singleton(ID));
        assertEquals(!initialState, store.getBoolean(PREFIX + ID));
    }
}

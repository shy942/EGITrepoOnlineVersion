/***/
package org.eclipse.ui.tests.activities;

import java.util.Iterator;
import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.ICategory;
import org.eclipse.ui.activities.NotDefinedException;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests that the Persistance class is catching malformed registry entries.
*
* @since 3.1
*/
public class PersistanceTest extends UITestCase {

    /**
* @param testName
*/
    public  PersistanceTest(String testName) {
        super(testName);
    }

    public void testCategoryPermutations() {
        try {
            IActivityManager manager = fWorkbench.getActivitySupport().getActivityManager();
            // should not be defined - missing name
            ICategory category = manager.getCategory("org.eclipse.ui.PT.C1");
            assertFalse(category.isDefined());
            // should be defined - missing desc
            category = manager.getCategory("org.eclipse.ui.PT.C2");
            assertTrue(category.isDefined());
            assertNotNull(category.getDescription());
            for (Iterator i = manager.getDefinedCategoryIds().iterator(); i.hasNext(); ) {
                if (manager.getCategory((String) i.next()).getName().equals("org.eclipse.ui.PT.C3")) {
                    fail("Found category that should not be.");
                }
            }
        } catch (NotDefinedException e) {
            fail(e.getMessage());
        }
    }

    public void testActivityRequirementBindings() {
        IActivityManager manager = fWorkbench.getActivitySupport().getActivityManager();
        IActivity activity = manager.getActivity("org.eclipse.ui.PT.A2");
        assertTrue(activity.getActivityRequirementBindings().isEmpty());
    }

    public void testActivityPatternBindings() {
        IActivityManager manager = fWorkbench.getActivitySupport().getActivityManager();
        IActivity activity = manager.getActivity("org.eclipse.ui.PT.A2");
        assertTrue(activity.getActivityPatternBindings().isEmpty());
    }

    public void testCategoryActivityBindings() {
        IActivityManager manager = fWorkbench.getActivitySupport().getActivityManager();
        ICategory category = manager.getCategory("org.eclipse.ui.PT.C2");
        assertTrue(category.getCategoryActivityBindings().isEmpty());
    }

    public void testActivityPermutations() {
        try {
            IActivityManager manager = fWorkbench.getActivitySupport().getActivityManager();
            // should not be defined - missing name
            IActivity activity = manager.getActivity("org.eclipse.ui.PT.A1");
            assertFalse(activity.isDefined());
            // should be defined - missing desc
            activity = manager.getActivity("org.eclipse.ui.PT.A2");
            assertTrue(activity.isDefined());
            assertNotNull(activity.getDescription());
            for (Iterator i = manager.getDefinedActivityIds().iterator(); i.hasNext(); ) {
                if (manager.getActivity((String) i.next()).getName().equals("org.eclipse.ui.PT.A3")) {
                    fail("Found activity that should not be.");
                }
            }
        } catch (NotDefinedException e) {
            fail(e.getMessage());
        }
    }
}

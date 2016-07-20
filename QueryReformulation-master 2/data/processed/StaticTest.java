/***/
package org.eclipse.ui.tests.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IActivityPatternBinding;
import org.eclipse.ui.activities.ICategory;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.activities.NotDefinedException;
import org.eclipse.ui.internal.activities.ActivityRequirementBinding;
import org.eclipse.ui.internal.activities.CategoryActivityBinding;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
*
* The static test reads activity definitions from the plugin.xml (in
* org.eclipse.ui.tests) file and valides its content.
*/
public class StaticTest extends UITestCase {

    private IActivityManager activityManager;

    private List categoryIds;

    private List activityIds;

    private List patternActivityIds;

    /**
* Constructor.
*
* @param testName
*            Test's name.
*/
    public  StaticTest(String testName) {
        super(testName);
        activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
        populateIds();
    }

    /**
* Populate the id arrays.
*
*/
    private void populateIds() {
        int index = 0;
        categoryIds = new ArrayList();
        for (index = 1; index <= 6; index++) {
            //$NON-NLS-1$
            categoryIds.add("org.eclipse.category" + Integer.toString(index));
        }
        activityIds = new ArrayList();
        for (index = 1; index <= 18; index++) {
            //$NON-NLS-1$
            activityIds.add("org.eclipse.activity" + Integer.toString(index));
        }
        patternActivityIds = new ArrayList();
        for (index = 0; index < 3; index++) {
            patternActivityIds.add(activityIds.toArray()[index]);
        }
    }

    /**
* Test the activity manager's content.
*
*/
    public void testActivityManager() {
        // Check the defined category Ids
        assertTrue(activityManager.getDefinedCategoryIds().containsAll(categoryIds));
        // Check the defined activity Ids
        assertTrue(activityManager.getDefinedActivityIds().containsAll(activityIds));
        // Check enabled activity Ids
        for (int index = 1; index <= 4; index++) {
            assertTrue(activityManager.getEnabledActivityIds().contains("org.eclipse.activity" + Integer.toString(index)));
        }
        // Check identifier
        IIdentifier activityIdentifier = activityManager.getIdentifier("org.eclipse.pattern1");
        Set activityIds = activityIdentifier.getActivityIds();
        assertTrue(activityIds.containsAll(patternActivityIds));
        assertTrue(activityIdentifier.getId().equals("org.eclipse.pattern1"));
    }

    /**
* Test an activitie's content.
*
*/
    public void testActivity() {
        IActivity first_activity = activityManager.getActivity((String) activityIds.toArray()[0]);
        // Check activity activity bindings for parent activity
        Set activityRequirementBindings = first_activity.getActivityRequirementBindings();
        for (int index = 2; index <= 7; index++) {
            assertTrue(activityRequirementBindings.contains(new ActivityRequirementBinding("org.eclipse.activity" + Integer.toString(index), "org.eclipse.activity1")));
        }
        // Check activity pattern bindings
        Set activityPatternBindings = first_activity.getActivityPatternBindings();
        assertTrue(activityPatternBindings.size() != 0);
        IActivityPatternBinding activityPatternBinding = (IActivityPatternBinding) activityPatternBindings.toArray()[0];
        assertTrue(activityPatternBinding.getActivityId().equals(first_activity.getId()));
        assertTrue(activityPatternBinding.getPattern().pattern().equals("org.eclipse.pattern1"));
        // Check description
        try {
            assertTrue(first_activity.getDescription().equals("description"));
        } catch (NotDefinedException e) {
            e.printStackTrace();
        }
        // Check activity id
        assertTrue(first_activity.getId().equals("org.eclipse.activity1"));
        // Check activity name
        try {
            assertTrue(first_activity.getName().equals("Activity 1"));
        } catch (NotDefinedException e) {
            e.printStackTrace();
        }
    }

    /**
* Test a category's content.
*
*/
    public void testCategory() {
        ICategory first_category = activityManager.getCategory((String) categoryIds.toArray()[0]);
        // Check category activity bindings
        Set categoryActivityBindings = first_category.getCategoryActivityBindings();
        for (int index = 1; index <= 4; index++) {
            assertTrue(categoryActivityBindings.contains(new CategoryActivityBinding("org.eclipse.activity" + Integer.toString(index), first_category.getId())));
        }
        try {
            // Check category description
            assertTrue(first_category.getDescription().equals("description"));
        } catch (NotDefinedException e) {
            e.printStackTrace();
        }
        // Check category id
        assertTrue(first_category.getId().equals("org.eclipse.category1"));
        try {
            // Check category name
            assertTrue(first_category.getName().equals("Category 1"));
        } catch (NotDefinedException e) {
            e.printStackTrace();
        }
    }
}

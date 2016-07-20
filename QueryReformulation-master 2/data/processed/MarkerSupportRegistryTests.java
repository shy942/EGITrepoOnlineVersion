/***/
package org.eclipse.ui.tests.markers;

import org.eclipse.ui.tests.harness.util.UITestCase;
import org.eclipse.ui.views.markers.internal.MarkerSupportRegistry;

/**
* MarkerSupportTests are tests for the markerSupport extension
* point.
* @since 3.2
*
*/
public class MarkerSupportRegistryTests extends UITestCase {

    /**
* Create an instance of the receiver.
*
* @param testName
*/
    public  MarkerSupportRegistryTests(String testName) {
        super(testName);
    }

    /**
* Test that the marker categories expected are found.
*
*/
    public void testMarkerCategories() {
        doTestCategory("org.eclipse.ui.tests.categoryTestMarker");
        doTestCategory("org.eclipse.ui.tests.testmarker");
        doTestCategory("org.eclipse.ui.tests.testmarker2");
    }

    /**
* Test that the marker type specified is in a category.
*
* @param string
*/
    private void doTestCategory(String string) {
        String category = MarkerSupportRegistry.getInstance().getCategory(string);
        assertFalse("No Category for" + string, category == null);
        assertTrue("Wrong Category for" + string, category.equals("Test Markers"));
    }
}

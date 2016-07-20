/***/
package org.eclipse.ui.tests.api;

import junit.framework.TestCase;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.PlatformUI;

public class IPerspectiveDescriptorTest extends TestCase {

    private IPerspectiveDescriptor[] fPerspectives;

    public  IPerspectiveDescriptorTest(String testName) {
        super(testName);
    }

    @Override
    public void setUp() {
        fPerspectives = PlatformUI.getWorkbench().getPerspectiveRegistry().getPerspectives();
    }

    /**
* Tests that the ids for all perspective descriptors are non-null and non-empty.
*/
    public void testGetId() {
        for (IPerspectiveDescriptor fPerspective : fPerspectives) {
            String id = fPerspective.getId();
            assertNotNull(id);
            assertTrue(id.length() > 0);
        }
    }

    /**
* Tests that the labels for all perspective descriptors are non-null and non-empty.
*/
    public void testGetLabel() {
        for (IPerspectiveDescriptor fPerspective : fPerspectives) {
            String label = fPerspective.getLabel();
            assertNotNull(label);
            assertTrue(label.length() > 0);
        }
    }

    /**
* Tests that the image descriptors for all perspective descriptors are non-null.
* <p>
* Note that some perspective extensions in the test suite do not specify an icon
* attribute.  getImageDescriptor should return a default image descriptor in this
* case.  This is a regression test for bug 68325.
* </p>
*/
    public void testGetImageDescriptor() {
        for (IPerspectiveDescriptor fPerspective : fPerspectives) {
            ImageDescriptor image = fPerspective.getImageDescriptor();
            assertNotNull(image);
        }
    }
}

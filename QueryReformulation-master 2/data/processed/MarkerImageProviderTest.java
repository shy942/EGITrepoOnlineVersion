/***/
package org.eclipse.ui.tests.adaptable;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests the markerImageProviders extension point.
*/
public class MarkerImageProviderTest extends UITestCase {

    public  MarkerImageProviderTest(String testName) {
        super(testName);
    }

    /**
* Tests the static form of the extension, where just a file path is given.
*/
    public void testStatic() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IMarker marker = null;
        try {
            marker = workspace.getRoot().createMarker(//$NON-NLS-1$
            "org.eclipse.ui.tests.testmarker");
        } catch (CoreException e) {
            fail(e.getMessage());
        }
        IWorkbenchAdapter adapter = marker.getAdapter(IWorkbenchAdapter.class);
        ImageDescriptor imageDesc = adapter.getImageDescriptor(marker);
        assertNotNull(imageDesc);
        //$NON-NLS-1$
        assertTrue(imageDesc.toString().indexOf("anything") != -1);
    }

    /**
* Tests the dynamic form of the extension, where an IMarkerImageProvider class is given.
*/
    public void testDynamic() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IMarker marker = null;
        try {
            marker = workspace.getRoot().createMarker(//$NON-NLS-1$
            "org.eclipse.ui.tests.testmarker2");
        } catch (CoreException e) {
            fail(e.getMessage());
        }
        IWorkbenchAdapter adapter = marker.getAdapter(IWorkbenchAdapter.class);
        ImageDescriptor imageDesc = adapter.getImageDescriptor(marker);
        assertNotNull(imageDesc);
        //$NON-NLS-1$
        assertTrue(imageDesc.toString().indexOf("anything") != -1);
    }
}

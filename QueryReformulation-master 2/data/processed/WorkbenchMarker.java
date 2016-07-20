/***/
package org.eclipse.ui.internal.ide.model;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IMarkerActionFilter;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.views.markers.MarkerPropertyTester;
import org.eclipse.ui.model.WorkbenchAdapter;

/**
* Model object for adapting IMarker objects to the IWorkbenchAdapter
* interface.
*/
public class WorkbenchMarker extends WorkbenchAdapter implements IMarkerActionFilter {

    @Override
    public ImageDescriptor getImageDescriptor(Object o) {
        if (!(o instanceof IMarker)) {
            return null;
        }
        return IDEWorkbenchPlugin.getDefault().getMarkerImageProviderRegistry().getImageDescriptor((IMarker) o);
    }

    @Override
    public String getLabel(Object o) {
        IMarker marker = (IMarker) o;
        //$NON-NLS-1$
        return marker.getAttribute(IMarker.MESSAGE, "");
    }

    @Override
    public Object getParent(Object o) {
        return ((IMarker) o).getResource();
    }

    /**
* Returns whether the specific attribute matches the state of the target
* object.
*
* @param target the target object
* @param name the attribute name
* @param value the attriute value
* @return <code>true</code> if the attribute matches; <code>false</code> otherwise
*/
    @Override
    public boolean testAttribute(Object target, String name, String value) {
        return MarkerPropertyTester.test((IMarker) target, name, value);
    }
}

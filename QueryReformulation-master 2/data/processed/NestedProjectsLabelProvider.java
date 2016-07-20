/***/
package org.eclipse.ui.internal.navigator.resources.nested;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class NestedProjectsLabelProvider extends LabelProvider {

    private WorkbenchLabelProvider labelProvider = new WorkbenchLabelProvider();

    @Override
    public String getText(Object element) {
        if (!(element instanceof IProject)) {
            return null;
        }
        IProject project = (IProject) element;
        IPath location = project.getLocation();
        if (location != null && !location.lastSegment().equals(project.getName())) {
            //$NON-NLS-1$ //$NON-NLS-2$
            return labelProvider.getText(element) + " (in " + location.lastSegment() + ")";
        }
        return null;
    }
}

/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.ui.IPerspectiveRegistry;

public class PerspContentProvider implements IStructuredContentProvider {

    @Override
    public Object[] getElements(Object element) {
        if (element instanceof IPerspectiveRegistry) {
            return ((IPerspectiveRegistry) element).getPerspectives();
        }
        return null;
    }
}

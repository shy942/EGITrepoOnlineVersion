/***/
package org.eclipse.ui.internal.navigator.resources.nested;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.navigator.CommonViewer;

/**
* Hides project if it is shown nested in some other location
* @author mistria
*
*/
public class HideTopLevelProjectIfNested extends ViewerFilter {

    //$NON-NLS-1$
    public static final String EXTENSION_ID = "org.eclipse.ui.navigator.resources.nested.HideTopLevelProjectIfNested";

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        CommonViewer commonViewer = (CommonViewer) viewer;
        if (commonViewer.getNavigatorContentService().getActivationService().isNavigatorExtensionActive(NestedProjectsContentProvider.EXTENSION_ID)) {
            if (element instanceof IProject) {
                if (parentElement != null) {
                    Object parentObject = null;
                    if (parentElement instanceof TreeNode) {
                        parentObject = ((TreeNode) parentElement).getValue();
                    } else if (parentElement instanceof TreePath) {
                        parentObject = ((TreePath) parentElement).getLastSegment();
                    } else {
                        parentObject = parentElement;
                    }
                    if (parentObject instanceof IAdaptable) {
                        IAdaptable parentAdaptable = (IAdaptable) parentObject;
                        if (parentAdaptable.getAdapter(IWorkspaceRoot.class) != null || parentAdaptable.getAdapter(IWorkingSet.class) != null) {
                            return !NestedProjectManager.getInstance().isShownAsNested((IProject) element);
                        }
                    }
                }
            }
        }
        return true;
    }
}

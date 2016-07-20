/***/
package org.eclipse.ui.internal.ide.model;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.model.WorkbenchAdapter;

/**
* IWorkbenchAdapter adapter for the IWorkspace object.
*/
public class WorkbenchWorkspace extends WorkbenchAdapter {

    @Override
    public Object[] getChildren(Object o) {
        IWorkspace workspace = (IWorkspace) o;
        return workspace.getRoot().getProjects();
    }

    @Override
    public ImageDescriptor getImageDescriptor(Object object) {
        return null;
    }

    /**
* getLabel method comment.
*/
    @Override
    public String getLabel(Object o) {
        //workspaces don't have a name
        return IDEWorkbenchMessages.Workspace;
    }
}

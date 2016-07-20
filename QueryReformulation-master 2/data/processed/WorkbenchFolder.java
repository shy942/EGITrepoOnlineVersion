/***/
package org.eclipse.ui.internal.ide.model;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
* An IWorkbenchAdapter that represents IFolders.
*/
public class WorkbenchFolder extends WorkbenchResource {

    /**
*	Answer the appropriate base image to use for the passed resource, optionally
*	considering the passed open status as well iff appropriate for the type of
*	passed resource
*/
    @Override
    protected ImageDescriptor getBaseImage(IResource resource) {
        return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
    }

    /**
* Returns the children of this container.
*/
    @Override
    public Object[] getChildren(Object o) {
        try {
            return ((IContainer) o).members();
        } catch (CoreException e) {
            return NO_CHILDREN;
        }
    }
}

/***/
package org.eclipse.ui.internal.ide;

import java.net.URI;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.ui.internal.ide.dialogs.IDEResourceInfoUtils;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
* A LinkedResourceDecorator decorates an element's image with a linked
* resource overlay.
*
* @since 2.1
*/
public class LinkedResourceDecorator implements ILightweightLabelDecorator {

    private static final ImageDescriptor LINK;

    private static final ImageDescriptor LINK_WARNING;

    static {
        LINK = AbstractUIPlugin.imageDescriptorFromPlugin(IDEWorkbenchPlugin.IDE_WORKBENCH, //$NON-NLS-1$
        "$nl$/icons/full/ovr16/link_ovr.png");
        LINK_WARNING = AbstractUIPlugin.imageDescriptorFromPlugin(IDEWorkbenchPlugin.IDE_WORKBENCH, //$NON-NLS-1$
        "$nl$/icons/full/ovr16/linkwarn_ovr.png");
    }

    /**
* Creates a new <code>LinkedResourceDecorator</code>.
*/
    public  LinkedResourceDecorator() {
    }

    /**
* @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(ILabelProviderListener)
*/
    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    /**
* @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
*/
    @Override
    public void dispose() {
    // no resources to dispose
    }

    /**
* @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
*/
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    /**
* @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(ILabelProviderListener)
*/
    @Override
    public void removeListener(ILabelProviderListener listener) {
    }

    /**
* Adds the linked resource overlay if the given element is a linked
* resource.
*
* @param element element to decorate
* @param decoration  The decoration we are adding to
* @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(Object, IDecoration)
*/
    @Override
    public void decorate(Object element, IDecoration decoration) {
        if (element instanceof IResource == false) {
            return;
        }
        IResource resource = (IResource) element;
        if (resource.isLinked() && !resource.isVirtual()) {
            IFileInfo fileInfo = null;
            URI location = resource.getLocationURI();
            if (location != null) {
                fileInfo = IDEResourceInfoUtils.getFileInfo(location);
            }
            if (fileInfo != null && fileInfo.exists()) {
                decoration.addOverlay(LINK);
            } else {
                decoration.addOverlay(LINK_WARNING);
            }
        }
    }
}
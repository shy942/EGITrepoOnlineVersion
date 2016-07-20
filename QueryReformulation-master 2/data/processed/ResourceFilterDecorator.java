/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;

/**
* Lightweight decorator for filtered container.
*/
public class ResourceFilterDecorator implements ILightweightLabelDecorator {

    //$NON-NLS-1$
    private static final String IMG_MARKERS_RESOURCE_FILTER_DECORATION_PATH = "ovr16/filterapplied_ovr.png";

    ImageDescriptor descriptorImage = null;

    /**
*
*/
    public  ResourceFilterDecorator() {
        descriptorImage = IDEWorkbenchPlugin.getIDEImageDescriptor(IMG_MARKERS_RESOURCE_FILTER_DECORATION_PATH);
    }

    @Override
    public void decorate(Object element, IDecoration decoration) {
        if (element instanceof IContainer == false) {
            return;
        }
        IContainer container = (IContainer) element;
        IResourceFilterDescription[] filters = null;
        try {
            filters = container.getFilters();
            if ((filters.length > 0) && (descriptorImage != null))
                decoration.addOverlay(descriptorImage, IDecoration.BOTTOM_RIGHT);
        } catch (CoreException e) {
        }
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
    }
}

/***/
package org.eclipse.ui.tests.decorators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.ui.tests.TestPlugin;

/**
* This is a contributor that uses the multi quadrant
* support.
*/
public class TestLightweightDecoratorMultipleQuadrantContributor implements ILightweightLabelDecorator {

    public static TestLightweightDecoratorMultipleQuadrantContributor contributor;

    private Set listeners = new HashSet();

    private ImageDescriptor descriptor;

    public  TestLightweightDecoratorMultipleQuadrantContributor() {
        contributor = this;
    }

    /*
* @see IBaseLabelProvider#addListener(ILabelProviderListener)
*/
    @Override
    public void addListener(ILabelProviderListener listener) {
        listeners.add(listener);
    }

    /*
* @see IBaseLabelProvider#dispose()
*/
    @Override
    public void dispose() {
        contributor = null;
        listeners = new HashSet();
    }

    /*
* @see IBaseLabelProvider#isLabelProperty(Object, String)
*/
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    /*
* @see IBaseLabelProvider#removeListener(ILabelProviderListener)
*/
    @Override
    public void removeListener(ILabelProviderListener listener) {
        listeners.remove(listener);
    }

    /**
* Refresh the listeners to update the decorators for
* element.
*/
    public void refreshListeners(Object element) {
        Iterator iterator = listeners.iterator();
        while (iterator.hasNext()) {
            LabelProviderChangedEvent event = new LabelProviderChangedEvent(this, element);
            ((ILabelProviderListener) iterator.next()).labelProviderChanged(event);
        }
    }

    /**
* @see org.eclipse.jface.viewers.ILightweightLabelDecorator#getOverlay(java.lang.Object)
*/
    public ImageDescriptor getOverlay(Object element) {
        Assert.isTrue(element instanceof IResource);
        if (descriptor == null) {
            URL source = TestPlugin.getDefault().getDescriptor().getInstallURL();
            try {
                descriptor = ImageDescriptor.createFromURL(new URL(source, "icons/binary_co.gif"));
            } catch (MalformedURLException exception) {
                return null;
            }
        }
        return descriptor;
    }

    /**
* @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object, org.eclipse.jface.viewers.IDecoration)
*/
    @Override
    public void decorate(Object element, IDecoration decoration) {
        decoration.addOverlay(getOverlay(element), IDecoration.BOTTOM_LEFT);
        decoration.addOverlay(getOverlay(element), IDecoration.BOTTOM_RIGHT);
        decoration.addOverlay(getOverlay(element), IDecoration.TOP_LEFT);
        decoration.addOverlay(getOverlay(element), IDecoration.TOP_RIGHT);
        decoration.addOverlay(getOverlay(element), IDecoration.UNDERLAY);
    }
}

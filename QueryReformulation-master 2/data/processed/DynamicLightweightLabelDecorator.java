/***/
package org.eclipse.ui.dynamic;

import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;

/**
* @since 3.1
*/
public class DynamicLightweightLabelDecorator implements ILightweightLabelDecorator {

    /**
*
*/
    public  DynamicLightweightLabelDecorator() {
        super();
    }

    @Override
    public void decorate(Object element, IDecoration decoration) {
        decoration.addSuffix("Light");
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

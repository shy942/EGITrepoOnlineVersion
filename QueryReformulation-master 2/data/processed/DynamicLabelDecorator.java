/***/
package org.eclipse.ui.dynamic;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
* @since 3.1
*/
public class DynamicLabelDecorator implements ILabelDecorator {

    /**
*
*/
    public  DynamicLabelDecorator() {
        super();
    }

    @Override
    public Image decorateImage(Image image, Object element) {
        return null;
    }

    @Override
    public String decorateText(String text, Object element) {
        return text + " F1 ";
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

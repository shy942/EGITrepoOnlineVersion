/***/
package org.eclipse.ui.tests.adaptable;

/*
* This decorator tests the null cases
*/
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
* @version 	1.0
* @author
*/
public class NullLabelDecorator implements ILabelDecorator {

    /*
* @see ILabelDecorator#decorateImage(Image, Object)
*/
    @Override
    public Image decorateImage(Image image, Object element) {
        return null;
    }

    /*
* @see ILabelDecorator#decorateText(String, Object)
*/
    @Override
    public String decorateText(String text, Object element) {
        return null;
    }

    /*
* @see IBaseLabelProvider#addListener(ILabelProviderListener)
*/
    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    /*
* @see IBaseLabelProvider#dispose()
*/
    @Override
    public void dispose() {
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
    }
}

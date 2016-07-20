/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.tests.internal.ForcedException;

/**
* @see ILabelDecorator
*/
public class HeavyNullTextDecorator implements ILabelDecorator {

    /**
* Whether we should fail with an exception
*/
    public static boolean fail = false;

    /**
*
*/
    public  HeavyNullTextDecorator() {
    }

    /**
* @see ILabelDecorator#addListener
*/
    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    /**
* @see ILabelDecorator#dispose
*/
    @Override
    public void dispose() {
    }

    /**
* @see ILabelDecorator#isLabelProperty
*/
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    /**
* @see ILabelDecorator#removeListener
*/
    @Override
    public void removeListener(ILabelProviderListener listener) {
    }

    /**
* @see ILabelDecorator#decorateImage
*/
    @Override
    public Image decorateImage(Image image, Object element) {
        return image;
    }

    /**
* @see ILabelDecorator#decorateText
*/
    @Override
    public String decorateText(String text, Object element) {
        if (fail) {
            fail = false;
            throw new ForcedException("Heavy text decorator boom");
        }
        return null;
    }
}

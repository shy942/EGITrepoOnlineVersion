/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;

/**
* @since 3.3
*
*/
public class CLabelImageProperty extends WidgetImageValueProperty {

    @Override
    Image doGetImageValue(Object source) {
        return ((CLabel) source).getImage();
    }

    @Override
    void doSetImageValue(Object source, Image value) {
        ((CLabel) source).setImage(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CLabel.image <Image>";
    }
}

/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

/**
* @since 3.3
*
*/
public class LabelImageProperty extends WidgetImageValueProperty {

    @Override
    Image doGetImageValue(Object source) {
        return ((Label) source).getImage();
    }

    @Override
    void doSetImageValue(Object source, Image value) {
        ((Label) source).setImage(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Label.image <Image>";
    }
}

/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;

/**
* @since 3.3
*
*/
public class ButtonImageProperty extends WidgetImageValueProperty {

    @Override
    Image doGetImageValue(Object source) {
        return ((Button) source).getImage();
    }

    @Override
    void doSetImageValue(Object source, Image value) {
        ((Button) source).setImage(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Button.image <Image>";
    }
}

/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Item;

/**
* @since 3.3
*
*/
public class ItemImageProperty extends WidgetImageValueProperty {

    @Override
    Image doGetImageValue(Object source) {
        return ((Item) source).getImage();
    }

    @Override
    void doSetImageValue(Object source, Image value) {
        ((Item) source).setImage(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Item.image <Image>";
    }
}

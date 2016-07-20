/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.graphics.Image;

/**
* @since 3.3
*
*/
public abstract class WidgetImageValueProperty extends WidgetValueProperty {

    @Override
    public Object getValueType() {
        return Image.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return doGetImageValue(source);
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        doSetImageValue(source, (Image) value);
    }

    abstract Image doGetImageValue(Object source);

    abstract void doSetImageValue(Object source, Image value);
}

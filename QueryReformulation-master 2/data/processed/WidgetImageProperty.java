/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;

/**
* @since 3.3
*
*/
public class WidgetImageProperty extends WidgetDelegatingValueProperty {

    private IValueProperty button;

    private IValueProperty cLabel;

    private IValueProperty item;

    private IValueProperty label;

    /**
*
*/
    public  WidgetImageProperty() {
        super(Image.class);
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof Button) {
            if (button == null)
                button = new ButtonImageProperty();
            return button;
        }
        if (source instanceof CLabel) {
            if (cLabel == null)
                cLabel = new CLabelImageProperty();
            return cLabel;
        }
        if (source instanceof Item) {
            if (item == null)
                item = new ItemImageProperty();
            return item;
        }
        if (source instanceof Label) {
            if (label == null)
                label = new LabelImageProperty();
            return label;
        }
        throw notSupported(source);
    }
}

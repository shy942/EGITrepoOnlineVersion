/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;

/**
* @since 3.3
*
*/
public class WidgetItemsProperty extends WidgetDelegatingListProperty {

    private IListProperty cCombo;

    private IListProperty combo;

    private IListProperty list;

    /**
*
*/
    public  WidgetItemsProperty() {
        super(String.class);
    }

    @Override
    protected IListProperty doGetDelegate(Object source) {
        if (source instanceof CCombo) {
            if (cCombo == null)
                cCombo = new CComboItemsProperty();
            return cCombo;
        }
        if (source instanceof Combo) {
            if (combo == null)
                combo = new ComboItemsProperty();
            return combo;
        }
        if (source instanceof List) {
            if (list == null)
                list = new ListItemsProperty();
            return list;
        }
        throw notSupported(source);
    }
}

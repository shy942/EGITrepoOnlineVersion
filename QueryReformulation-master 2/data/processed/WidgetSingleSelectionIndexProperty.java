/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;

/**
* @since 3.3
*
*/
public final class WidgetSingleSelectionIndexProperty extends WidgetDelegatingValueProperty {

    private IValueProperty cCombo;

    private IValueProperty combo;

    private IValueProperty list;

    private IValueProperty table;

    /**
*
*/
    public  WidgetSingleSelectionIndexProperty() {
        super(Integer.TYPE);
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof CCombo) {
            if (cCombo == null)
                cCombo = new CComboSingleSelectionIndexProperty();
            return cCombo;
        }
        if (source instanceof Combo) {
            if (combo == null)
                combo = new ComboSingleSelectionIndexProperty();
            return combo;
        }
        if (source instanceof List) {
            if (list == null)
                list = new ListSingleSelectionIndexProperty();
            return list;
        }
        if (source instanceof Table) {
            if (table == null)
                table = new TableSingleSelectionIndexProperty();
            return table;
        }
        throw notSupported(source);
    }
}

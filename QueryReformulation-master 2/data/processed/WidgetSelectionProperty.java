/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;

/**
* @since 3.3
*
*/
public final class WidgetSelectionProperty extends WidgetDelegatingValueProperty {

    private IValueProperty button;

    private IValueProperty cCombo;

    private IValueProperty combo;

    private IValueProperty dateTime;

    private IValueProperty list;

    private IValueProperty menuItem;

    private IValueProperty scale;

    private IValueProperty slider;

    private IValueProperty spinner;

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof Button) {
            if (button == null)
                button = new ButtonSelectionProperty();
            return button;
        }
        if (source instanceof CCombo) {
            if (cCombo == null)
                cCombo = new CComboSelectionProperty();
            return cCombo;
        }
        if (source instanceof Combo) {
            if (combo == null)
                combo = new ComboSelectionProperty();
            return combo;
        }
        if (source instanceof DateTime) {
            if (dateTime == null)
                dateTime = new DateTimeSelectionProperty();
            return dateTime;
        }
        if (source instanceof List) {
            if (list == null)
                list = new ListSelectionProperty();
            return list;
        }
        if (source instanceof MenuItem) {
            if (menuItem == null)
                menuItem = new MenuItemSelectionProperty();
            return menuItem;
        }
        if (source instanceof Scale) {
            if (scale == null)
                scale = new ScaleSelectionProperty();
            return scale;
        }
        if (source instanceof Slider) {
            if (slider == null)
                slider = new SliderSelectionProperty();
            return slider;
        }
        if (source instanceof Spinner) {
            if (spinner == null)
                spinner = new SpinnerSelectionProperty();
            return spinner;
        }
        throw notSupported(source);
    }
}

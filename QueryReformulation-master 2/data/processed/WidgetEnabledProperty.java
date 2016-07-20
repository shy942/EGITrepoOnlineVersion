/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.ToolItem;

public class WidgetEnabledProperty extends WidgetDelegatingValueProperty {

    IValueProperty control;

    IValueProperty menu;

    IValueProperty menuItem;

    IValueProperty scrollBar;

    IValueProperty toolItem;

    /**
*
*/
    public  WidgetEnabledProperty() {
        super(Boolean.TYPE);
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof Control) {
            if (control == null)
                control = new ControlEnabledProperty();
            return control;
        }
        if (source instanceof Menu) {
            if (menu == null)
                menu = new MenuEnabledProperty();
            return menu;
        }
        if (source instanceof MenuItem) {
            if (menuItem == null)
                menuItem = new MenuItemEnabledProperty();
            return menuItem;
        }
        if (source instanceof ScrollBar) {
            if (scrollBar == null)
                scrollBar = new ScrollBarEnabledProperty();
            return scrollBar;
        }
        if (source instanceof ToolItem) {
            if (toolItem == null)
                toolItem = new ToolItemEnabledProperty();
            return toolItem;
        }
        throw notSupported(source);
    }
}

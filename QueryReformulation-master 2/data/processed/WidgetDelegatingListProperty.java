/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.list.DelegatingListProperty;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.ISWTObservableList;
import org.eclipse.jface.databinding.swt.IWidgetListProperty;
import org.eclipse.swt.widgets.Widget;

abstract class WidgetDelegatingListProperty extends DelegatingListProperty implements IWidgetListProperty {

    RuntimeException notSupported(Object source) {
        return new IllegalArgumentException(//$NON-NLS-1$//$NON-NLS-2$
        "Widget [" + source.getClass().getName() + "] is not supported.");
    }

    public  WidgetDelegatingListProperty(Object elementType) {
        super(elementType);
    }

    @Override
    public ISWTObservableList observe(Widget widget) {
        return (ISWTObservableList) observe(DisplayRealm.getRealm(widget.getDisplay()), widget);
    }
}

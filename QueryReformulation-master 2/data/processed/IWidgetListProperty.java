/***/
package org.eclipse.jface.databinding.swt;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.swt.widgets.Widget;

/**
* {@link IListProperty} for observing an SWT Widget
*
* @since 1.3
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IWidgetListProperty extends IListProperty {

    /**
* Returns an {@link ISWTObservableList} observing this list property on the
* given widget
*
* @param widget
*            the source widget
* @return an observable list observing this list property on the given
*         widget
*/
    public ISWTObservableList observe(Widget widget);
}

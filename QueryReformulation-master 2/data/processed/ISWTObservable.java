/***/
package org.eclipse.jface.databinding.swt;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.swt.widgets.Widget;

/**
* {@link IObservable} observing an SWT widget.
*
* @since 1.1
*
*/
public interface ISWTObservable extends IObservable {

    /**
* Returns the widget of this observable
*
* @return the widget
*/
    public Widget getWidget();
}

/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Scale;

/**
* @since 3.3
*
*/
public class ScaleSelectionProperty extends WidgetIntValueProperty {

    /**
*
*/
    public  ScaleSelectionProperty() {
        super(SWT.Selection);
    }

    @Override
    int doGetIntValue(Object source) {
        return ((Scale) source).getSelection();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Scale) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Scale.selection <int>";
    }
}

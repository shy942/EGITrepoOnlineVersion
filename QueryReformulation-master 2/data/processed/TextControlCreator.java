/***/
package org.eclipse.jface.fieldassist;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
* An {@link IControlCreator} for SWT Text controls. This is a convenience class
* for creating text controls to be supplied to a decorated field.
*
* @since 3.2
* @deprecated As of 3.3, clients should use {@link ControlDecoration} instead
*             of {@link DecoratedField}.
*
*/
@Deprecated
public class TextControlCreator implements IControlCreator {

    @Override
    public Control createControl(Composite parent, int style) {
        return new Text(parent, style);
    }
}

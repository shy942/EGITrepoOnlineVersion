/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

/*
* A page used as a filler for nodes in the property page dialog
* for which no page is suppplied.
*/
public class EmptyPropertyPage extends PropertyPage {

    /**
* Creates empty composite for this page content.
*/
    @Override
    protected Control createContents(Composite parent) {
        return new Composite(parent, SWT.NULL);
    }
}

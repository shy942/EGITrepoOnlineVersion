/***/
package org.eclipse.ui.tests.rcp.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

/**
* Minimal view, for the RCP tests.
*/
public class EmptyView extends ViewPart {

    //$NON-NLS-1$
    public static final String ID = "org.eclipse.ui.tests.rcp.util.EmptyView";

    private Label label;

    public  EmptyView() {
    // do nothing
    }

    @Override
    public void createPartControl(Composite parent) {
        label = new Label(parent, SWT.NONE);
        label.setText("Empty view");
    }

    @Override
    public void setFocus() {
        label.setFocus();
    }
}

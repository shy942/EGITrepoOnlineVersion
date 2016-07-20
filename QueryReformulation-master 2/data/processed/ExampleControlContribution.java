/***/
package org.eclipse.ui.examples.contributions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

/**
* Moved from org.eclipse.ui.examples.readmetool
*
* @since 3.3
*/
public class ExampleControlContribution extends WorkbenchWindowControlContribution {

    @Override
    protected Control createControl(Composite parent) {
        // Create a composite to place the label in
        Composite comp = new Composite(parent, SWT.NONE);
        // Give some room around the control
        FillLayout layout = new FillLayout();
        layout.marginHeight = 2;
        layout.marginWidth = 2;
        comp.setLayout(layout);
        // Create a label for the trim.
        Label ccCtrl = new Label(comp, SWT.BORDER | SWT.CENTER);
        ccCtrl.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
        ccCtrl.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        //$NON-NLS-1$ //$NON-NLS-2$
        ccCtrl.setText(" Ctrl Contrib (" + getSideName(getCurSide()) + ")");
        //$NON-NLS-1$
        ccCtrl.setToolTipText("Ctrl Contrib Tooltip");
        return comp;
    }

    private String getSideName(int side) {
        if (side == SWT.TOP)
            //$NON-NLS-1$
            return "Top";
        if (side == SWT.BOTTOM)
            //$NON-NLS-1$
            return "Bottom";
        if (side == SWT.LEFT)
            //$NON-NLS-1$
            return "Left";
        if (side == SWT.RIGHT)
            //$NON-NLS-1$
            return "Right";
        //$NON-NLS-1$
        return "Unknown Side";
    }
}

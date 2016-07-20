/***/
package org.eclipse.jface.action;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.util.Policy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
* An abstract contribution item implementation for adding an arbitrary
* SWT control to a tool bar.
* Note, however, that these items cannot be contributed to menu bars.
* <p>
* The <code>createControl</code> framework method must be implemented
* by concrete subclasses.
* </p>
*/
public abstract class ControlContribution extends ContributionItem {

    /**
* Creates a control contribution item with the given id.
*
* @param id the contribution item id
*/
    protected  ControlContribution(String id) {
        super(id);
    }

    /**
* Computes the width of the given control which is being added
* to a tool bar.  This is needed to determine the width of the tool bar item
* containing the given control.
* <p>
* The default implementation of this framework method returns
* <code>control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x</code>.
* Subclasses may override if required.
* </p>
*
* @param control the control being added
* @return the width of the control
*/
    protected int computeWidth(Control control) {
        return control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x;
    }

    /**
* Creates and returns the control for this contribution item
* under the given parent composite.
* <p>
* This framework method must be implemented by concrete
* subclasses.
* </p>
*
* @param parent the parent composite
* @return the new control, must not be <code>null</code>
*/
    protected abstract Control createControl(Composite parent);

    /**
* The control item implementation of this <code>IContributionItem</code>
* method calls the <code>createControl</code> framework method.
* Subclasses must implement <code>createControl</code> rather than
* overriding this method.
*/
    @Override
    public final void fill(Composite parent) {
        createControl(parent);
    }

    /**
* The control item implementation of this <code>IContributionItem</code>
* method throws an exception since controls cannot be added to menus.
*/
    @Override
    public final void fill(Menu parent, int index) {
        //$NON-NLS-1$
        Assert.isTrue(false, "Can't add a control to a menu");
    }

    /**
* The control item implementation of this <code>IContributionItem</code>
* method calls the <code>createControl</code> framework method to
* create a control under the given parent, and then creates
* a new tool item to hold it.
* Subclasses must implement <code>createControl</code> rather than
* overriding this method.
*/
    @Override
    public final void fill(ToolBar parent, int index) {
        Control control = createControl(parent);
        if (control == null) {
            Policy.logException(new IllegalStateException(//$NON-NLS-1$
            "createControl(Composite) of " + getClass() + //$NON-NLS-1$
            " returned null, cannot fill toolbar"));
        } else {
            ToolItem ti = new ToolItem(parent, SWT.SEPARATOR, index);
            ti.setControl(control);
            ti.setWidth(computeWidth(control));
        }
    }
}

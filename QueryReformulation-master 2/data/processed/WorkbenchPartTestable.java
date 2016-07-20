/***/
package org.eclipse.ui.internal.testing;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.testing.IWorkbenchPartTestable;

/**
* Implementation of {@link IWorkbenchPartTestable}.
*
* @since 3.3
*/
public class WorkbenchPartTestable implements IWorkbenchPartTestable {

    private Composite composite;

    /**
* Create a new instance of this class based on the provided part.
*
* @param partSite the part to test
*/
    public  WorkbenchPartTestable(PartSite partSite) {
        Composite paneComposite = (Composite) partSite.getModel().getWidget();
        Control[] paneChildren = paneComposite.getChildren();
        this.composite = ((Composite) paneChildren[0]);
    }

    @Override
    public Composite getControl() {
        return composite;
    }
}

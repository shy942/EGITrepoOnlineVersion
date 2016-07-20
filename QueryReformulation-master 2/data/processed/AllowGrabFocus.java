/***/
package org.eclipse.ui.internal.tweaklets;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

/**
* @since 3.3
*
*/
public class AllowGrabFocus extends GrabFocus {

    @Override
    public boolean grabFocusAllowed(IWorkbenchPart part) {
        return true;
    }

    @Override
    public void init(Display display) {
    }

    @Override
    public void dispose() {
    }
}

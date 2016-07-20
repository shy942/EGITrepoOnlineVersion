/***/
package org.eclipse.ui.internal.layout;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

/**
* Contains various methods for manipulating layouts
*
* @since 3.0
*/
public class LayoutUtil {

    /**
* Should be called whenever a control's contents have changed. Will
* trigger a layout parent controls if necessary.
*
* @param changedControl
*/
    public static void resize(Control changedControl) {
        Composite parent = changedControl.getParent();
        Layout parentLayout = parent.getLayout();
        if (parentLayout instanceof ICachingLayout) {
            ((ICachingLayout) parentLayout).flush(changedControl);
        }
        if (parent instanceof Shell) {
            parent.layout(true);
        } else {
            Rectangle currentBounds = parent.getBounds();
            resize(parent);
            // layout. Otherwise, we need to manually force it to layout again.
            if (currentBounds.equals(parent.getBounds())) {
                parent.layout(true);
            }
        }
    }
}

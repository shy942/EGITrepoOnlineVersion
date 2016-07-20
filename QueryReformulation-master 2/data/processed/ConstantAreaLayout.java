/***/
package org.eclipse.ui.tests.performance.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

/**
* A very efficient (but useless) layout with complicated preferred size behavior.
* Its preferred size attempts to maintain a constant area. This can be used for
* performance testing other layouts (by attaching this to child Composites in the
* layout being tested). It will give a good estimate as to how the layout will
* handle wrapping widgets.
*
* @since 3.1
*/
public class ConstantAreaLayout extends Layout {

    private int area;

    private int preferredWidth;

    public  ConstantAreaLayout(int area, int preferredWidth) {
        this.area = area;
        this.preferredWidth = preferredWidth;
    }

    @Override
    protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
        if (wHint == 0 || hHint == 0) {
            return new Point(1, 1);
        }
        if (wHint == SWT.DEFAULT) {
            if (hHint == SWT.DEFAULT) {
                wHint = preferredWidth;
            } else {
                wHint = area / hHint;
            }
        }
        if (hHint == SWT.DEFAULT) {
            hHint = area / wHint;
        }
        return new Point(wHint, hHint);
    }

    @Override
    protected void layout(Composite composite, boolean flushCache) {
    }
}

/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
* The ForegroundColorDecorator is for testing the foreground enablement.
*/
public class ForegroundColorDecorator implements ILightweightLabelDecorator {

    public static final String ID = "org.eclipse.ui.tests.foregroundDecorator";

    public static Color color;

    @Override
    public void decorate(Object element, IDecoration decoration) {
        if (color == null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                @Override
                public void run() {
                    setUpColor();
                }
            });
        }
        decoration.setForegroundColor(color);
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
    // TODO Auto-generated method stub
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub
    }

    public static void setUpColor() {
        color = PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_YELLOW);
    }
}

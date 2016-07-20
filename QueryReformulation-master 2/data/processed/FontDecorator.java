/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.PlatformUI;

/**
* The FontDecorator is for testing the font enablement.
*/
public class FontDecorator implements ILightweightLabelDecorator {

    public static final String ID = "org.eclipse.ui.tests.fontDecorator";

    public static Font font;

    @Override
    public void decorate(Object element, IDecoration decoration) {
        if (font == null) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                @Override
                public void run() {
                    setUpFont();
                }
            });
        }
        decoration.setFont(font);
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
    }

    /**
* Setup the font used by this decorator.
*/
    public static void setUpFont() {
        font = JFaceResources.getFont(JFaceResources.HEADER_FONT);
    }
}

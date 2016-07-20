/***/
package org.eclipse.ui.tests.navigator.extension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
* @since 3.3
*
*/
public class TestLabelProviderCyan extends TestStyledLabelProvider {

    public static TestLabelProviderCyan instance;

    @Override
    protected void initSubclass() {
        backgroundColor = Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
        backgroundColorName = "Cyan";
        font = new Font(Display.getDefault(), boldFontData);
        image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW);
        instance = this;
    }
}

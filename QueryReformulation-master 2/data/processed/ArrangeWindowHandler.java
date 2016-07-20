/***/
package org.eclipse.e4.ui.workbench.renderers.swt.cocoa;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.internal.cocoa.NSApplication;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
* @since 4.1
*/
public class ArrangeWindowHandler extends AbstractWindowHandler {

    @CanExecute
    public boolean canExecute(@Optional Display display) {
        if (display == null) {
            return false;
        }
        boolean isEnabled = false;
        Shell[] shells = Display.getDefault().getShells();
        // not all windows should be in minimized state
        for (int i = 0; i < shells.length; i++) {
            if (shells[i].view.window().isKeyWindow()) {
                isEnabled = true;
                break;
            }
        }
        return isEnabled;
    }

    @Execute
    public void execute() {
        NSApplication app = NSApplication.sharedApplication();
        app.arrangeInFront(app);
    }
}

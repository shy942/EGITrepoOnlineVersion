/***/
package org.eclipse.e4.ui.workbench.renderers.swt.cocoa;

import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.internal.cocoa.NSWindow;
import org.eclipse.swt.widgets.Shell;

/**
* @since 4.1
*/
public class MinimizeWindowHandler extends AbstractWindowHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
        if (shell == null) {
            return;
        }
        NSWindow window = shell.view.window();
        if (window == null) {
            return;
        }
        window.miniaturize(window);
    }
}

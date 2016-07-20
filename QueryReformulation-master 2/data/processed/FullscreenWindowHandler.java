/***/
package org.eclipse.e4.ui.workbench.renderers.swt.cocoa;

import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;

/**
* @since 4.2
*/
public class FullscreenWindowHandler extends AbstractWindowHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
        if (!shell.isDisposed()) {
            shell.setFullScreen(!shell.getFullScreen());
        }
    }
}

/***/
package org.eclipse.ui.internal.cocoa;

import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.internal.cocoa.NSWindow;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.tweaklets.TitlePathUpdater;

/**
*
* @since 3.7
*
*/
public class CocoaTitlePathUpdater extends TitlePathUpdater {

    public void updateTitlePath(Shell window, String path) {
        if (window == null || window.isDisposed())
            return;
        if (path == null)
            path = "";
        NSWindow nsWindow = window.getShell().view.window();
        NSString filePathString = NSString.stringWith(path);
        nsWindow.setRepresentedFilename(filePathString);
    }
}

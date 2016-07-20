/***/
package org.eclipse.ui.internal.ide.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.ConfigurationInfo;

/**
* Copies the configuration data present in the about dialog to the clipboard.
*
* @since 3.4
*/
public class CopyConfigToClipboardHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        String contents = ConfigurationInfo.getSystemSummary();
        Clipboard clipboard = null;
        try {
            clipboard = new Clipboard(HandlerUtil.getActiveShell(event).getDisplay());
            clipboard.setContents(new Object[] { contents }, new Transfer[] { TextTransfer.getInstance() });
        } finally {
            if (clipboard != null) {
                clipboard.dispose();
            }
        }
        return null;
    }
}

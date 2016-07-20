/***/
package org.eclipse.ui.internal.ide.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.ConfigurationInfo;

/**
* Copies the build ID to the clipboard. Useful for debugging and bug
* reporting/verification.
*
* @since 3.4
*
*/
public class CopyBuildIdToClipboardHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final String buildId = ConfigurationInfo.getBuildId();
        if (buildId == null || buildId.length() == 0)
            //$NON-NLS-1$
            throw new ExecutionException("No build ID in this instance.");
        Clipboard clipboard = null;
        try {
            clipboard = new Clipboard(HandlerUtil.getActiveShell(event).getDisplay());
            clipboard.setContents(new Object[] { buildId }, new Transfer[] { TextTransfer.getInstance() });
        } finally {
            if (clipboard != null) {
                clipboard.dispose();
            }
        }
        return null;
    }
}

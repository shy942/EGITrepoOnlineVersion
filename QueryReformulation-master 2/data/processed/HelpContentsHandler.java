/***/
package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.PlatformUI;

/**
* @author Prakash G.R.
* @since 3.7
*
*/
public class HelpContentsHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        BusyIndicator.showWhile(null, new Runnable() {

            @Override
            public void run() {
                PlatformUI.getWorkbench().getHelpSystem().displayHelp();
            }
        });
        return null;
    }
}

/***/
package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;

/**
* A handler that displays the key assist dialog when executed.
*
* @since 3.1
*/
public class ShowKeyAssistHandler extends WorkbenchWindowHandlerDelegate {

    /**
* Opens the key assistant. This should never be called until initialization
* occurs.
*
* @param event
*            Ignored
* @return <code>null</code>
*/
    @Override
    public Object execute(final ExecutionEvent event) {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final IBindingService bindingService = workbench.getService(IBindingService.class);
        bindingService.openKeyAssistDialog();
        return null;
    }
}

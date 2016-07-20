/***/
package org.eclipse.e4.ui.internal.workbench.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

public class UnimplementedHandler {

    @Execute
    public void execute(IWorkbench workbench) {
        //$NON-NLS-1$
        System.err.println("This command handler is not implemented.");
    }
}

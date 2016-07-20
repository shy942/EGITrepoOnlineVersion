/***/
package org.eclipse.ui.internal.about;

import org.eclipse.osgi.util.NLS;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.dialogs.AboutDialog;

/**
* Creates an About dialog and opens it.
*/
public class AboutAction extends Action implements ActionFactory.IWorkbenchAction {

    /**
* The workbench window; or <code>null</code> if this action has been
* <code>dispose</code>d.
*/
    private IWorkbenchWindow workbenchWindow;

    /**
* Creates a new <code>AboutAction</code>.
*
* @param window the window
*/
    public  AboutAction(IWorkbenchWindow window) {
        if (window == null) {
            throw new IllegalArgumentException();
        }
        this.workbenchWindow = window;
        // use message with no fill-in
        IProduct product = Platform.getProduct();
        String productName = null;
        if (product != null) {
            productName = product.getName();
        }
        if (productName == null) {
            //$NON-NLS-1$
            productName = "";
        }
        setText(NLS.bind(WorkbenchMessages.AboutAction_text, productName));
        setToolTipText(NLS.bind(WorkbenchMessages.AboutAction_toolTip, productName));
        //$NON-NLS-1$
        setId("about");
        setActionDefinitionId(IWorkbenchCommandConstants.HELP_ABOUT);
        window.getWorkbench().getHelpSystem().setHelp(this, IWorkbenchHelpContextIds.ABOUT_ACTION);
    }

    @Override
    public void run() {
        // make sure action is not disposed
        if (workbenchWindow != null) {
            new AboutDialog(workbenchWindow.getShell()).open();
        }
    }

    @Override
    public void dispose() {
        workbenchWindow = null;
    }
}

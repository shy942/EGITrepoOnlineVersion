/***/
package org.eclipse.ui.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;

/**
* Standard action for scrubbing the local content in the local file system of
* the selected resources and all of their descendents.
* <p>
* This class may be instantiated; it is not intended to be subclassed.
* </p>
* @deprecated This class is obsolete; there is no support in the workspace
* for scrubbing local content.
* @noextend This class is not intended to be subclassed by clients.
*/
@Deprecated
public class ScrubLocalAction extends WorkspaceAction {

    /**
* The id of this action.
*/
    //$NON-NLS-1$
    public static final String ID = "org.eclipse.ui.ScrubLocalAction";

    /**
* Creates a new action.
*
* @param shell the shell for any dialogs
*/
    public  ScrubLocalAction(Shell shell) {
        super(shell, IDEWorkbenchMessages.ScrubLocalAction_text);
        setToolTipText(IDEWorkbenchMessages.ScrubLocalAction_toolTip);
        setId(ID);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IIDEHelpContextIds.SCRUB_LOCAL_ACTION);
    }

    @Override
    protected String getOperationMessage() {
        return IDEWorkbenchMessages.ScrubLocalAction_progress;
    }

    @Override
    protected String getProblemsMessage() {
        return IDEWorkbenchMessages.ScrubLocalAction_problemsMessage;
    }

    @Override
    protected String getProblemsTitle() {
        return IDEWorkbenchMessages.ScrubLocalAction_problemsTitle;
    }

    @Override
    protected void invokeOperation(IResource resource, IProgressMonitor monitor) throws CoreException {
        resource.setLocal(false, IResource.DEPTH_INFINITE, monitor);
    }

    /**
* The <code>ScrubLocalAction</code> implementation of this
* <code>SelectionListenerAction</code> method ensures that this action is
* disabled if any of the selections are not resources.
*/
    @Override
    protected boolean updateSelection(IStructuredSelection s) {
        return super.updateSelection(s) && getSelectedNonResources().size() == 0;
    }
}

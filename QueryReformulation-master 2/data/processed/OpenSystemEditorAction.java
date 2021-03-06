/***/
package org.eclipse.ui.actions;

import java.util.Iterator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.part.FileEditorInput;

/**
* Standard action for opening a system editor on the currently selected file
* resource.
* <p>
* This class may be instantiated; it is not intended to be subclassed.
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public class OpenSystemEditorAction extends SelectionListenerAction {

    /**
* The id of this action.
*/
    public static final String ID = PlatformUI.PLUGIN_ID + //$NON-NLS-1$
    ".OpenSystemEditorAction";

    /**
* The workbench page to open the editor in.
*/
    private IWorkbenchPage workbenchPage;

    /**
* Creates a new action that will open system editors on the then-selected file
* resources.
*
* @param page the workbench page in which to open the editor
*/
    public  OpenSystemEditorAction(IWorkbenchPage page) {
        super(IDEWorkbenchMessages.OpenSystemEditorAction_text);
        setToolTipText(IDEWorkbenchMessages.OpenSystemEditorAction_toolTip);
        setId(ID);
        if (page == null) {
            throw new IllegalArgumentException();
        }
        page.getWorkbenchWindow().getWorkbench().getHelpSystem().setHelp(this, IIDEHelpContextIds.OPEN_SYSTEM_EDITOR_ACTION);
        this.workbenchPage = page;
    }

    /**
* Return the workbench page to open the editor in.
*
* @return the workbench page to open the editor in
*/
    /* package */
    final IWorkbenchPage getWorkbenchPage() {
        return workbenchPage;
    }

    /**
* Opens a system editor on the given file resource.
*
* @param file the file resource
*/
    /* package */
    void openFile(IFile file) {
        try {
            getWorkbenchPage().openEditor(new FileEditorInput(file), IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
        } catch (PartInitException e) {
            DialogUtil.openError(getWorkbenchPage().getWorkbenchWindow().getShell(), IDEWorkbenchMessages.OpenSystemEditorAction_dialogTitle, e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        Iterator itr = getSelectedResources().iterator();
        while (itr.hasNext()) {
            IResource resource = (IResource) itr.next();
            if (resource instanceof IFile) {
                openFile((IFile) resource);
            }
        }
    }

    /**
* The <code>OpenSystemEditorAction</code> implementation of this
* <code>SelectionListenerAction</code> method enables the action only
* if the selection contains just file resources.
*/
    @Override
    protected boolean updateSelection(IStructuredSelection selection) {
        return super.updateSelection(selection) && selectionIsOfType(IResource.FILE);
    }
}

/***/
package org.eclipse.ui.wizards.newresource;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFolderMainPage;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.wizards.newresource.ResourceMessages;

/**
* Standard workbench wizard that create a new folder resource in the workspace.
* <p>
* This class may be instantiated and used without further configuration;
* this class is not intended to be subclassed.
* </p>
* <p>
* Example:
* <pre>
* IWorkbenchWizard wizard = new BasicNewFolderResourceWizard();
* wizard.init(workbench, selection);
* WizardDialog dialog = new WizardDialog(shell, wizard);
* dialog.open();
* </pre>
* During the call to <code>open</code>, the wizard dialog is presented to the
* user. When the user hits Finish, a folder resource at the user-specified
* workspace path is created, the dialog closes, and the call to
* <code>open</code> returns.
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public class BasicNewFolderResourceWizard extends BasicNewResourceWizard {

    /**
* The wizard id for creating new folders in the workspace.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String WIZARD_ID = "org.eclipse.ui.wizards.new.folder";

    private WizardNewFolderMainPage mainPage;

    /**
* Creates a wizard for creating a new folder resource in the workspace.
*/
    public  BasicNewFolderResourceWizard() {
        super();
    }

    @Override
    public void addPages() {
        super.addPages();
        mainPage = new WizardNewFolderMainPage(ResourceMessages.NewFolder_text, getSelection());
        addPage(mainPage);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        super.init(workbench, currentSelection);
        setWindowTitle(ResourceMessages.NewFolder_title);
        setNeedsProgressMonitor(true);
    }

    @Override
    protected void initializeDefaultPageImageDescriptor() {
        //$NON-NLS-1$
        ImageDescriptor desc = IDEWorkbenchPlugin.getIDEImageDescriptor("wizban/newfolder_wiz.png");
        setDefaultPageImageDescriptor(desc);
    }

    @Override
    public boolean performFinish() {
        IFolder folder = mainPage.createNewFolder();
        if (folder == null) {
            return false;
        }
        selectAndReveal(folder);
        return true;
    }
}

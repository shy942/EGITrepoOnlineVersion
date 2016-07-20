/***/
package org.eclipse.ui.wizards.datatransfer;

import java.util.List;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.eclipse.ui.internal.wizards.datatransfer.WizardFileSystemResourceImportPage1;

/**
* Standard workbench wizard for importing resources from the local file system
* into the workspace.
* <p>
* This class may be instantiated and used without further configuration;
* this class is not intended to be subclassed.
* </p>
* <p>
* Example:
* <pre>
* IWizard wizard = new FileSystemImportWizard();
* wizard.init(workbench, selection);
* WizardDialog dialog = new WizardDialog(shell, wizard);
* dialog.open();
* </pre>
* During the call to <code>open</code>, the wizard dialog is presented to the
* user. When the user hits Finish, the user-selected files are imported
* into the workspace, the dialog closes, and the call to <code>open</code>
* returns.
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public class FileSystemImportWizard extends Wizard implements IImportWizard {

    private IWorkbench workbench;

    private IStructuredSelection selection;

    private WizardFileSystemResourceImportPage1 mainPage;

    /**
* Creates a wizard for importing resources into the workspace from
* the file system.
*/
    public  FileSystemImportWizard() {
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings section = workbenchSettings.getSection(//$NON-NLS-1$
        "FileSystemImportWizard");
        if (section == null) {
            //$NON-NLS-1$
            section = workbenchSettings.addNewSection("FileSystemImportWizard");
        }
        setDialogSettings(section);
    }

    @Override
    public void addPages() {
        super.addPages();
        mainPage = new WizardFileSystemResourceImportPage1(workbench, selection);
        addPage(mainPage);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        this.workbench = workbench;
        this.selection = currentSelection;
        List selectedResources = IDE.computeSelectedResources(currentSelection);
        if (!selectedResources.isEmpty()) {
            this.selection = new StructuredSelection(selectedResources);
        }
        setWindowTitle(DataTransferMessages.DataTransfer_importTitle);
        //$NON-NLS-1$
        setDefaultPageImageDescriptor(IDEWorkbenchPlugin.getIDEImageDescriptor("wizban/importdir_wiz.png"));
        setNeedsProgressMonitor(true);
    }

    @Override
    public boolean performFinish() {
        return mainPage.finish();
    }
}

/***/
package org.eclipse.ui.wizards.datatransfer;

import java.util.List;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.eclipse.ui.internal.wizards.datatransfer.WizardArchiveFileResourceExportPage1;

/**
* Standard workbench wizard for exporting resources from the workspace
* to a zip file.
* <p>
* This class may be instantiated and used without further configuration;
* this class is not intended to be subclassed.
* </p>
* <p>
* Example:
* <pre>
* IWizard wizard = new ZipFileExportWizard();
* wizard.init(workbench, selection);
* WizardDialog dialog = new WizardDialog(shell, wizard);
* dialog.open();
* </pre>
* During the call to <code>open</code>, the wizard dialog is presented to the
* user. When the user hits Finish, the user-selected workspace resources
* are exported to the user-specified zip file, the dialog closes, and the call
* to <code>open</code> returns.
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public class ZipFileExportWizard extends Wizard implements IExportWizard {

    private IStructuredSelection selection;

    private WizardArchiveFileResourceExportPage1 mainPage;

    /**
* Creates a wizard for exporting workspace resources to a zip file.
*/
    public  ZipFileExportWizard() {
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings section = workbenchSettings.getSection(//$NON-NLS-1$
        "ZipFileExportWizard");
        if (section == null) {
            //$NON-NLS-1$
            section = workbenchSettings.addNewSection("ZipFileExportWizard");
        }
        setDialogSettings(section);
    }

    @Override
    public void addPages() {
        super.addPages();
        mainPage = new WizardArchiveFileResourceExportPage1(selection);
        addPage(mainPage);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        this.selection = currentSelection;
        List selectedResources = IDE.computeSelectedResources(currentSelection);
        if (!selectedResources.isEmpty()) {
            this.selection = new StructuredSelection(selectedResources);
        }
        setWindowTitle(DataTransferMessages.DataTransfer_export);
        //$NON-NLS-1$
        setDefaultPageImageDescriptor(IDEWorkbenchPlugin.getIDEImageDescriptor("wizban/exportzip_wiz.png"));
        setNeedsProgressMonitor(true);
    }

    @Override
    public boolean performFinish() {
        return mainPage.finish();
    }
}

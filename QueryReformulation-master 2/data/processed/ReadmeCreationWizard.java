/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
* This class implements the interface required by the workbench
* for all 'New' wizards.  This wizard creates readme files.
*/
public class ReadmeCreationWizard extends Wizard implements INewWizard {

    private IStructuredSelection selection;

    private IWorkbench workbench;

    private ReadmeCreationPage mainPage;

    @Override
    public void addPages() {
        mainPage = new ReadmeCreationPage(workbench, selection);
        addPage(mainPage);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        //$NON-NLS-1$
        setWindowTitle(MessageUtil.getString("New_Readme_File"));
        setDefaultPageImageDescriptor(ReadmeImages.README_WIZARD_BANNER);
    }

    @Override
    public boolean performFinish() {
        return mainPage.finish();
    }
}

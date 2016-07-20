/***/
package org.eclipse.ui.examples.contributions.model;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
* Use the service to create a person.
*
* @since 3.4
*/
public class PersonWizard extends Wizard implements INewWizard {

    private PersonWizardPage mainPage;

    private IWorkbench workbench;

    @Override
    public void addPages() {
        mainPage = new PersonWizardPage(workbench);
        addPage(mainPage);
    }

    @Override
    public boolean performFinish() {
        return mainPage.finish();
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
    }
}

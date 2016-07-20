/***/
package org.eclipse.ui.tests.datatransfer;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class TestImportWizard extends Wizard implements IImportWizard {

    public  TestImportWizard() {
        super();
    }

    @Override
    public boolean performFinish() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
    // TODO Auto-generated method stub
    }
}

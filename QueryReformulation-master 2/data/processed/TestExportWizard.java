/***/
package org.eclipse.ui.tests.datatransfer;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public class TestExportWizard extends Wizard implements IExportWizard {

    public  TestExportWizard() {
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

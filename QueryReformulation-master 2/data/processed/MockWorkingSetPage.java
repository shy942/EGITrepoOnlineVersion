/***/
package org.eclipse.ui.tests.api;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.dialogs.IWorkingSetPage;

/**
* Tests the org.eclipse.ui.workingSets extension point.
*/
public class MockWorkingSetPage extends WizardPage implements IWorkingSetPage {

    private IWorkingSet workingSet;

    /**
* Creates a new instance of the receiver.
*/
    public  MockWorkingSetPage() {
        super("MockWorkingSetPage", "Test Working Set", //$NON-NLS-1$ $NON-NLS-2$
        ImageDescriptor.getMissingImageDescriptor());
    }

    /**
* Overrides method in WizardPage.
*
* @see org.eclipse.jface.wizard.WizardPage#createControl(Composite)
*/
    @Override
    public void createControl(Composite parent) {
    }

    /**
* Implements IWorkingSetPage.
*
* @see org.eclipse.ui.dialogs.IWorkingSetPage#getSelection()
*/
    @Override
    public IWorkingSet getSelection() {
        return workingSet;
    }

    /**
* Implements IWorkingSetPage.
*
* @see org.eclipse.ui.dialogs.IWorkingSetPage#setSelection(IWorkingSet)
*/
    @Override
    public void setSelection(IWorkingSet workingSet) {
    }

    /**
* @see org.eclipse.ui.dialogs.IWorkingSetPage#finish()
*/
    @Override
    public void finish() {
    }
}

/***/
package org.eclipse.ui.internal.quickaccess;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.actions.NewWizardShortcutAction;
import org.eclipse.ui.wizards.IWizardDescriptor;

/**
* @since 3.3
*
*/
public class WizardElement extends QuickAccessElement {

    //$NON-NLS-1$
    private static final String separator = " - ";

    private IWizardDescriptor wizardDescriptor;

    /* package */
     WizardElement(IWizardDescriptor wizardDescriptor, WizardProvider wizardProvider) {
        super(wizardProvider);
        this.wizardDescriptor = wizardDescriptor;
    }

    @Override
    public void execute() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            NewWizardShortcutAction wizardAction = new NewWizardShortcutAction(window, wizardDescriptor);
            wizardAction.run();
        }
    }

    @Override
    public String getId() {
        return wizardDescriptor.getId();
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return wizardDescriptor.getImageDescriptor();
    }

    @Override
    public String getLabel() {
        return wizardDescriptor.getLabel() + separator + wizardDescriptor.getDescription();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((wizardDescriptor == null) ? 0 : wizardDescriptor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final WizardElement other = (WizardElement) obj;
        if (wizardDescriptor == null) {
            if (other.wizardDescriptor != null)
                return false;
        } else if (!wizardDescriptor.equals(other.wizardDescriptor))
            return false;
        return true;
    }
}

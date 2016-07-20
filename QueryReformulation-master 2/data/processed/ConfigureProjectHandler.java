/***/
package org.eclipse.ui.internal.wizards.datatransfer;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;

/**
* Opens the {@link SmartImportWizard} on selected project.
*
* @since 3.12
*
*/
public class ConfigureProjectHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent arg0) {
        IProject project = null;
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
        if (selection instanceof IStructuredSelection) {
            Object item = ((IStructuredSelection) selection).getFirstElement();
            project = Adapters.adapt(item, IProject.class);
        }
        if (project == null) {
            return null;
        }
        SmartImportWizard wizard = new SmartImportWizard();
        wizard.setInitialImportSource(project.getLocation().toFile());
        // inherit workingSets
        Set<IWorkingSet> workingSets = new HashSet();
        for (IWorkingSet workingSet : PlatformUI.getWorkbench().getWorkingSetManager().getWorkingSets()) {
            for (IAdaptable element : workingSet.getElements()) {
                if (project.getAdapter(element.getClass()) == element) {
                    workingSets.add(workingSet);
                }
            }
        }
        wizard.setInitialWorkingSets(workingSets);
        return new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard).open();
    }
}

/***/
package org.eclipse.ui.tests.adaptable;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.tests.decorators.TestDecoratorContributor;

public class ResourceAction implements IObjectActionDelegate {

    Object selectedItem;

    /*
* @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
*/
    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    /*
* @see IActionDelegate#run(IAction)
*/
    @Override
    public void run(IAction action) {
        if (selectedItem != null) {
            TestDecoratorContributor.contributor.refreshListeners(selectedItem);
        }
    }

    /*
* @see IActionDelegate#selectionChanged(IAction, ISelection)
*/
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structured = (IStructuredSelection) selection;
            if (structured.isEmpty()) {
                selectedItem = null;
            } else {
                selectedItem = structured.getFirstElement();
            }
        }
    }
}

/***/
package org.eclipse.ui.dynamic;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
* @since 3.1
*/
public class MockObjectActionDelegate implements IObjectActionDelegate, IActionDelegate {

    /**
*
*/
    public  MockObjectActionDelegate() {
        super();
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    @Override
    public void run(IAction action) {
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }
}

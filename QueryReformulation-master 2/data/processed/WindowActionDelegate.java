/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
* This class shows how <code>IActionDelegate</code> implementations
* should be used for global action registration for menu
* and tool bars. Action proxy object is created in the
* workbench based on presentation information in the plugin.xml
* file. Delegate is not loaded until the first time the user
* presses the button or selects the menu. Based on the action
* availability, it is possible that the button will disable
* instead of executing.
*/
public class WindowActionDelegate implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow window;

    private ISelection selection;

    @Override
    public void dispose() {
    // do nothing
    }

    @Override
    public void init(IWorkbenchWindow window) {
        this.window = window;
    }

    /**
* The <code>WindowActionDelegate</code> implementation of this
* <code>IActionDelegate</code> method
* launches a stand-alone dialog that contains a list of sections for
* the selected readme file in the navigator.
*/
    @Override
    public void run(IAction action) {
        SectionsDialog dialog = new SectionsDialog(window.getShell(), ReadmeModelFactory.getInstance().getSections(selection));
        dialog.open();
    }

    /**
* The <code>WindowActionDelegate</code> implementation of this
* <code>IActionDelegate</code> method
* does nothing - we will let simple rules in the XML
* config file react to selections.
*/
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }
}

/***/
package org.eclipse.ui.internal.ide.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.dialogs.CleanDialog;

/**
* The clean action replaces the rebuild actions. Clean will discard all built
* state for all projects in the workspace, and deletes all problem markers.
* The next time a build is run, projects will have to be built from scratch.
* Technically this is only necessary if an incremental builder misbehaves.
*
* @since 3.0
*/
public class BuildCleanAction extends Action implements ActionFactory.IWorkbenchAction {

    private IWorkbenchWindow window;

    /**
* Creates a new BuildCleanAction
*
* @param window The window for parenting this action
*/
    public  BuildCleanAction(IWorkbenchWindow window) {
        super(IDEWorkbenchMessages.Workbench_buildClean);
        //$NON-NLS-1$
        setActionDefinitionId("org.eclipse.ui.project.cleanAction");
        this.window = window;
    }

    @Override
    public void dispose() {
    //nothing to dispose
    }

    @Override
    public void run() {
        IProject[] selected = BuildUtilities.findSelectedProjects(window);
        new CleanDialog(window, selected).open();
    }
}

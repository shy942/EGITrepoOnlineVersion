/***/
package org.eclipse.ui.navigator.resources;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.internal.navigator.framelist.GoIntoAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

/**
* Provides the Go Into action for the {@link ProjectExplorer}
*
* @since 3.4
*
*/
public class GoIntoActionProvider extends CommonActionProvider {

    private GoIntoAction goIntoAction;

    @Override
    public void init(ICommonActionExtensionSite anActionSite) {
        anActionSite.getViewSite().getShell();
        CommonViewer viewer = (CommonViewer) anActionSite.getStructuredViewer();
        goIntoAction = new GoIntoAction(viewer.getFrameList());
    }

    @Override
    public void dispose() {
        goIntoAction.dispose();
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        actionBars.setGlobalActionHandler(IWorkbenchActionConstants.GO_INTO, goIntoAction);
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
        //$NON-NLS-1$
        menu.appendToGroup("group.new", goIntoAction);
    }

    @Override
    public void updateActionBars() {
        goIntoAction.update();
    }
}

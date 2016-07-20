/***/
package org.eclipse.ui.examples.navigator.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
* @since 3.2
*
*/
public class PropertyActionProvider extends CommonActionProvider {

    private OpenPropertyAction openAction;

    /**
* Construct Property Action provider.
*/
    public  PropertyActionProvider() {
    }

    /* (non-Javadoc)
* @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
*/
    public void init(ICommonActionExtensionSite aSite) {
        ICommonViewerSite viewSite = aSite.getViewSite();
        if (viewSite instanceof ICommonViewerWorkbenchSite) {
            ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
            openAction = new OpenPropertyAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
        }
    }

    /* (non-Javadoc)
* @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
*/
    public void fillActionBars(IActionBars actionBars) {
        /* Set up the property open action when enabled. */
        if (openAction.isEnabled())
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
    }

    /* (non-Javadoc)
* @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
*/
    public void fillContextMenu(IMenuManager menu) {
        if (openAction.isEnabled())
            menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openAction);
    }
}

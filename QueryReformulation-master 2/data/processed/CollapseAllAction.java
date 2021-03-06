/***/
package org.eclipse.ui.internal.navigator.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.handlers.CollapseAllHandler;
import org.eclipse.ui.internal.navigator.CommonNavigatorMessages;
import org.eclipse.ui.navigator.CommonViewer;

/**
* This action delegate collapses all expanded elements in a Navigator view.
*
*/
public class CollapseAllAction extends Action {

    private final CommonViewer commonViewer;

    /**
* Create the CollapseAll action.
*
* @param aViewer
*            The viewer to be collapsed.
*/
    public  CollapseAllAction(CommonViewer aViewer) {
        super(CommonNavigatorMessages.CollapseAllActionDelegate_0);
        setToolTipText(CommonNavigatorMessages.CollapseAllActionDelegate_0);
        setActionDefinitionId(CollapseAllHandler.COMMAND_ID);
        commonViewer = aViewer;
    }

    @Override
    public void run() {
        if (commonViewer != null) {
            commonViewer.collapseAll();
        }
    }
}

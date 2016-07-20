/***/
package org.eclipse.ui.internal.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.util.PrefUtil;

/**
* Action to open the dynamic help.
*
* @since 3.1
*/
public class DynamicHelpAction extends Action implements IWorkbenchAction {

    /**
* The workbench window; or <code>null</code> if this action has been
* <code>dispose</code>d.
*/
    private IWorkbenchWindow workbenchWindow;

    /**
* Zero-arg constructor to allow cheat sheets to reuse this action.
*/
    public  DynamicHelpAction() {
        this(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
    }

    /**
* Constructor for use by ActionFactory.
*
* @param window
*            the window
*/
    public  DynamicHelpAction(IWorkbenchWindow window) {
        if (window == null) {
            throw new IllegalArgumentException();
        }
        this.workbenchWindow = window;
        setActionDefinitionId(IWorkbenchCommandConstants.HELP_DYNAMIC_HELP);
        // support for allowing a product to override the text for the action
        String overrideText = PrefUtil.getAPIPreferenceStore().getString(IWorkbenchPreferenceConstants.DYNAMIC_HELP_ACTION_TEXT);
        if ("".equals(overrideText)) {
            //$NON-NLS-1$
            setText(appendAccelerator(WorkbenchMessages.DynamicHelpAction_text));
            setToolTipText(WorkbenchMessages.DynamicHelpAction_toolTip);
        } else {
            setText(appendAccelerator(overrideText));
            setToolTipText(Action.removeMnemonics(overrideText));
        }
        window.getWorkbench().getHelpSystem().setHelp(this, IWorkbenchHelpContextIds.DYNAMIC_HELP_ACTION);
    }

    private String appendAccelerator(String text) {
        //	return text + "\t" + KeyStroke.getInstance(SWT.F1).format(); //$NON-NLS-1$
        return text;
    }

    @Override
    public void run() {
        if (workbenchWindow == null) {
            // action has been disposed
            return;
        }
        // This may take a while, so use the busy indicator
        BusyIndicator.showWhile(null, new Runnable() {

            @Override
            public void run() {
                workbenchWindow.getWorkbench().getHelpSystem().displayDynamicHelp();
            }
        });
    }

    @Override
    public void dispose() {
        workbenchWindow = null;
    }
}

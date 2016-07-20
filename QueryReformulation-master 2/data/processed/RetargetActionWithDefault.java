/***/
package org.eclipse.ui.internal.ide.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.RetargetAction;

/**
* A specialization of RetargetAction that allows for specification of a default
* handler when the active part does not supply one.  Enablement of this
* action is based on enablement of the handler, or enablement of the default
* handler if no explicit handler is available.
*
* @since 3.1
*/
public class RetargetActionWithDefault extends RetargetAction {

    private IAction defaultHandler;

    /**
* Constructs a RetargetActionWithDefault with the given action id and text.
*
* @param actionID the retargetable action id
* @param text the action's text, or <code>null</code> if there is no text
*/
    public  RetargetActionWithDefault(String actionID, String text) {
        super(actionID, text);
    }

    /*
* Method declared on RetargetAction.
*/
    @Override
    protected void setActionHandler(IAction newHandler) {
        super.setActionHandler(newHandler);
        // might be needed since the active part has changed.
        if (newHandler == null) {
            super.setActionHandler(defaultHandler);
        }
    }

    /**
* Sets the default handler for this action.
* @param handler An action handler, or <code>null</code>
*/
    public void setDefaultHandler(IAction handler) {
        this.defaultHandler = handler;
        if (getActionHandler() == null && handler != null) {
            super.setActionHandler(handler);
        }
    }
}

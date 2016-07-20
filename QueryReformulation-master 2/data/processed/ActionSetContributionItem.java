/***/
package org.eclipse.ui.internal;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.SubContributionItem;

/**
* This class marks a sub contribution item as belonging to
* an action set.
*/
public class ActionSetContributionItem extends SubContributionItem implements IActionSetContributionItem {

    /**
* The action set id.
*/
    private String actionSetId;

    /**
* Constructs a new item
*/
    public  ActionSetContributionItem(IContributionItem item, String actionSetId) {
        super(item);
        this.actionSetId = actionSetId;
    }

    /**
* Returns the action set id.
*/
    @Override
    public String getActionSetId() {
        return actionSetId;
    }

    /**
* Sets the action set id.
*/
    @Override
    public void setActionSetId(String newActionSetId) {
        actionSetId = newActionSetId;
    }

    @Override
    public String toString() {
        return //$NON-NLS-1$
        "ActionSetContributionItem [id=" + actionSetId + ", visible=" + isVisible() + //$NON-NLS-1$ //$NON-NLS-2$
        "]";
    }
}

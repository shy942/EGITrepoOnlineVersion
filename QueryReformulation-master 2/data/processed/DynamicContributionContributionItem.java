/***/
package org.eclipse.e4.ui.workbench.renderers.swt;

import org.eclipse.e4.ui.model.application.ui.menu.MDynamicMenuContribution;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;

/**
* This item currently serves as a placeholder to determine the correct location
* of a dynamic menu contribution entry.
*/
class DynamicContributionContributionItem extends ContributionItem {

    private MDynamicMenuContribution model;

    private IMenuListener menuListener = new IMenuListener() {

        @Override
        public void menuAboutToShow(IMenuManager manager) {
            manager.markDirty();
        }
    };

    /**
* Create the item and associated model;
*
* @param item
*/
    public  DynamicContributionContributionItem(MDynamicMenuContribution item) {
        super(item.getElementId());
        model = item;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    /**
* @return the model element
*/
    public MDynamicMenuContribution getModel() {
        return model;
    }

    @Override
    public void setParent(IContributionManager parent) {
        if (getParent() instanceof IMenuManager) {
            IMenuManager menuMgr = (IMenuManager) getParent();
            menuMgr.removeMenuListener(menuListener);
        }
        if (parent instanceof IMenuManager) {
            IMenuManager menuMgr = (IMenuManager) parent;
            menuMgr.addMenuListener(menuListener);
        }
        super.setParent(parent);
    }
}

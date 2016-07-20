/***/
package org.eclipse.jface.action;

import org.eclipse.core.runtime.Assert;

/**
* A <code>SubCoolBarManager</code> monitors the additional and removal of
* items from a parent manager so that visibility of the entire set can be changed as a
* unit.
*
* @since 3.0
*/
public class SubCoolBarManager extends SubContributionManager implements ICoolBarManager {

    /**
* Constructs a new manager.
*
* @param mgr the parent manager.  All contributions made to the
*      <code>SubCoolBarManager</code> are forwarded and appear in the
*      parent manager.
*/
    public  SubCoolBarManager(ICoolBarManager mgr) {
        super(mgr);
        Assert.isNotNull(mgr);
    }

    @Override
    public void add(IToolBarManager toolBarManager) {
        Assert.isNotNull(toolBarManager);
        super.add(new ToolBarContributionItem(toolBarManager));
    }

    @Override
    public int getStyle() {
        // constructor
        return ((ICoolBarManager) getParent()).getStyle();
    }

    /**
* Returns the parent cool bar manager that this sub-manager contributes to.
*
* @return the parent cool bar manager
*/
    protected final ICoolBarManager getParentCoolBarManager() {
        // thing we accept in the construtor.
        return (ICoolBarManager) getParent();
    }

    @Override
    public boolean getLockLayout() {
        return getParentCoolBarManager().getLockLayout();
    }

    @Override
    public void setLockLayout(boolean value) {
    }

    @Override
    public IMenuManager getContextMenuManager() {
        return null;
    }

    @Override
    public void setContextMenuManager(IMenuManager menuManager) {
    // do nothing
    }

    @Override
    public void update(boolean force) {
        // This method is not governed by visibility.  The client may
        // call <code>setVisible</code> and then force an update.  At that
        // point we need to update the parent.
        getParentCoolBarManager().update(force);
    }
}

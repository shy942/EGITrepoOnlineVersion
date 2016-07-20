/***/
package org.eclipse.jface.action;

/**
* A <code>SubToolBarManager</code> monitors the additional and removal of
* items from a parent manager so that visibility of the entire set can be changed as a
* unit.
*/
public class SubToolBarManager extends SubContributionManager implements IToolBarManager {

    /**
* Constructs a new manager.
*
* @param mgr the parent manager.  All contributions made to the
*      <code>SubToolBarManager</code> are forwarded and appear in the
*      parent manager.
*/
    public  SubToolBarManager(IToolBarManager mgr) {
        super(mgr);
    }

    /**
* @return the parent toolbar manager that this sub-manager contributes to
*/
    protected final IToolBarManager getParentToolBarManager() {
        // thing we accept in the construtor.
        return (IToolBarManager) getParent();
    }

    @Override
    public void update(boolean force) {
        // This method is not governed by visibility.  The client may
        // call <code>setVisible</code> and then force an update.  At that
        // point we need to update the parent.
        getParentToolBarManager().update(force);
    }
}

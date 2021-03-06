/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.WorkbenchException;

/**
* This class extends regular plugin action with the
* additional requirement that the delegate has
* to implement interface IViewActionDeelgate.
* This interface has one additional method (init)
* whose purpose is to initialize the delegate with
* the view part in which the action is intended to run.
*/
public final class ViewPluginAction extends PartPluginAction {

    private IViewPart viewPart;

    /**
* This class adds the requirement that action delegates
* loaded on demand implement IViewActionDelegate
*/
    public  ViewPluginAction(IConfigurationElement actionElement, IViewPart viewPart, String id, int style) {
        super(actionElement, id, style);
        this.viewPart = viewPart;
        registerSelectionListener(viewPart);
    }

    @Override
    protected IActionDelegate validateDelegate(Object obj) throws WorkbenchException {
        if (obj instanceof IViewActionDelegate) {
            return (IViewActionDelegate) obj;
        } else {
            throw new WorkbenchException(//$NON-NLS-1$
            "Action must implement IViewActionDelegate");
        }
    }

    @Override
    protected void initDelegate() {
        super.initDelegate();
        ((IViewActionDelegate) getDelegate()).init(viewPart);
    }

    /**
* Returns true if the view has been set
* The view may be null after the constructor is called and
* before the view is stored.  We cannot create the delegate
* at that time.
*/
    @Override
    public boolean isOkToCreateDelegate() {
        return super.isOkToCreateDelegate() && viewPart != null;
    }

    @Override
    public void dispose() {
        unregisterSelectionListener(viewPart);
        super.dispose();
    }
}

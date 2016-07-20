/***/
package org.eclipse.ui.internal.menus;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;

/**
* Add workbench specific information to a standard control contribution.
* Allows the client derivatives to access the IWorkbenchWindow hosting
* the control as well as the side of the workbench that the control is
* currently being displayed on.
*
* @since 3.3
*
*/
public abstract class InternalControlContribution extends ControlContribution {

    private IWorkbenchWindow wbw;

    private int curSide;

    /**
* @param id
*/
    protected  InternalControlContribution(String id) {
        super(id);
    }

    public  InternalControlContribution() {
        //$NON-NLS-1$
        this("unknown ID");
    }

    /**
* @return Returns the wbw.
*/
    public IWorkbenchWindow getWorkbenchWindow() {
        return wbw;
    }

    /**
* @param wbw The wbw to set.
*/
    public void setWorkbenchWindow(IWorkbenchWindow wbw) {
        this.wbw = wbw;
    }

    /**
* @return Returns the curSide.
*/
    public int getCurSide() {
        return curSide;
    }

    /**
* @param curSide The curSide to set.
*/
    public void setCurSide(int curSide) {
        this.curSide = curSide;
    }

    public int getOrientation() {
        if (getCurSide() == SWT.LEFT || getCurSide() == SWT.RIGHT)
            return SWT.VERTICAL;
        return SWT.HORIZONTAL;
    }
}

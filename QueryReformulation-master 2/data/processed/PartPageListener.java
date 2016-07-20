/***/
package org.eclipse.ui.tests.multipageeditor;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

/**
* @since 3.5
*
*/
public class PartPageListener implements IPartListener2, IPageChangedListener {

    public int pageChangeCount;

    public PageChangedEvent currentChangeEvent;

    @Override
    public void partActivated(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partBroughtToTop(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partClosed(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partDeactivated(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partHidden(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partInputChanged(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partOpened(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void partVisible(IWorkbenchPartReference partRef) {
    // TODO Auto-generated method stub
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        pageChangeCount++;
        currentChangeEvent = event;
    }
}

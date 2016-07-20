/***/
package org.eclipse.ui.tests.api.workbenchpart;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.services.IWorkbenchLocationService;
import org.eclipse.ui.part.ViewPart;

/**
* @since 3.4
*
*/
public class LifecycleView extends ViewPart {

    public static final String ID = "org.eclipse.ui.tests.LifecycleView";

    public boolean callWidgetDispose = false;

    public boolean callSiteDispose = false;

    public boolean callPartDispose = false;

    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);
    }

    @Override
    public void createPartControl(Composite parent) {
        IActionBars actionBars = getViewSite().getActionBars();
        IToolBarManager toolBarManager = actionBars.getToolBarManager();
        toolBarManager.add(new Action("Hi") {
        });
        actionBars.updateActionBars();
        ((ToolBarManager) toolBarManager).getControl().addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(DisposeEvent e) {
                callWidgetDispose = true;
            }
        });
    }

    @Override
    public void setFocus() {
    }

    @Override
    public void dispose() {
        IWorkbenchLocationService wls = getSite().getService(IWorkbenchLocationService.class);
        if (wls.getPartSite() == null) {
            callSiteDispose = true;
        }
        callPartDispose = true;
        super.dispose();
    }
}

/***/
package org.eclipse.ui.examples.propertysheet;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
* Page for the content outliner
*/
public class PropertySheetContentOutlinePage extends ContentOutlinePage {

    private IAdaptable model;

    /**
* Create a new instance of the reciver using adapatable
* as the model.
*/
    public  PropertySheetContentOutlinePage(IAdaptable adaptable) {
        this.model = adaptable;
    }

    /**
* Creates the control and registers the popup menu for this page
* Menu id "org.eclipse.ui.examples.propertysheet.outline"
*/
    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        TreeViewer viewer = getTreeViewer();
        viewer.setContentProvider(new WorkbenchContentProvider());
        viewer.setLabelProvider(new WorkbenchLabelProvider());
        viewer.setInput(this.model);
        viewer.expandAll();
        // Configure the context menu.
        //$NON-NLS-1$
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + //$NON-NLS-1$
        "-end"));
        Menu menu = menuMgr.createContextMenu(viewer.getTree());
        viewer.getTree().setMenu(menu);
        // Be sure to register it so that other plug-ins can add actions.
        getSite().registerContextMenu("org.eclipse.ui.examples.propertysheet.outline", menuMgr, //$NON-NLS-1$
        viewer);
    }
}

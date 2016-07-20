/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkingSetFilterActionGroup;
import org.eclipse.jface.resource.ImageDescriptor;

/**
* Menu contribution item which shows a working set.
*
* @since 2.1
*/
public class WorkingSetMenuContributionItem extends ContributionItem {

    private Image image;

    private int id;

    private IWorkingSet workingSet;

    private WorkingSetFilterActionGroup actionGroup;

    /**
* Returns the id of this menu contribution item
*
* @param id numerical id
* @return String string id
*/
    public static String getId(int id) {
        //$NON-NLS-1$
        return WorkingSetMenuContributionItem.class.getName() + "." + id;
    }

    /**
* Creates a new instance of the receiver.
*
* @param id sequential id of the new instance
* @param actionGroup the action group this contribution item is created in
*/
    public  WorkingSetMenuContributionItem(int id, WorkingSetFilterActionGroup actionGroup, IWorkingSet workingSet) {
        super(getId(id));
        Assert.isNotNull(actionGroup);
        Assert.isNotNull(workingSet);
        this.id = id;
        this.actionGroup = actionGroup;
        this.workingSet = workingSet;
    }

    /**
* Adds a menu item for the working set.
* Overrides method from ContributionItem.
*
* @see org.eclipse.jface.action.ContributionItem#fill(Menu,int)
*/
    @Override
    public void fill(Menu menu, int index) {
        MenuItem mi = new MenuItem(menu, SWT.RADIO, index);
        //$NON-NLS-1$  //$NON-NLS-2$
        mi.setText("&" + id + " " + workingSet.getLabel());
        mi.setSelection(workingSet.equals(actionGroup.getWorkingSet()));
        mi.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                IWorkingSetManager manager = PlatformUI.getWorkbench().getWorkingSetManager();
                actionGroup.setWorkingSet(workingSet);
                manager.addRecentWorkingSet(workingSet);
            }
        });
        if (image == null) {
            ImageDescriptor imageDescriptor = workingSet.getImageDescriptor();
            if (imageDescriptor != null)
                image = imageDescriptor.createImage();
        }
        mi.setImage(image);
    }

    /**
* Overridden to always return true and force dynamic menu building.
*/
    @Override
    public boolean isDynamic() {
        return true;
    }

    /*
* @see org.eclipse.jface.action.ContributionItem#dispose()
* @since 3.3
*/
    @Override
    public void dispose() {
        if (image != null && !image.isDisposed())
            image.dispose();
        image = null;
        super.dispose();
    }
}

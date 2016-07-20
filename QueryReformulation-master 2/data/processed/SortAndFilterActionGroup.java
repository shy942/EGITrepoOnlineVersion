/***/
package org.eclipse.ui.views.navigator;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.internal.views.navigator.ResourceNavigatorMessages;

/**
* This is the action group for the sort and filter actions.
* @deprecated as of 3.5, use the Common Navigator Framework classes instead
*/
@Deprecated
public class SortAndFilterActionGroup extends ResourceNavigatorActionGroup {

    private SortViewAction sortByTypeAction;

    private SortViewAction sortByNameAction;

    private FilterSelectionAction filterAction;

    /**
* Constructor.
*
* @param navigator
*/
    public  SortAndFilterActionGroup(IResourceNavigator navigator) {
        super(navigator);
    }

    @Override
    protected void makeActions() {
        sortByNameAction = new SortViewAction(navigator, false);
        sortByTypeAction = new SortViewAction(navigator, true);
        filterAction = new FilterSelectionAction(navigator, ResourceNavigatorMessages.ResourceNavigator_filterText);
        filterAction.setDisabledImageDescriptor(//$NON-NLS-1$
        getImageDescriptor("dlcl16/filter_ps.png"));
        filterAction.setImageDescriptor(//$NON-NLS-1$
        getImageDescriptor("elcl16/filter_ps.png"));
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        IMenuManager menu = actionBars.getMenuManager();
        IMenuManager submenu = new MenuManager(ResourceNavigatorMessages.ResourceNavigator_sort);
        menu.add(submenu);
        submenu.add(sortByNameAction);
        submenu.add(sortByTypeAction);
        menu.add(filterAction);
    }

    @Override
    public void updateActionBars() {
        int criteria = navigator.getComparator().getCriteria();
        sortByNameAction.setChecked(criteria == ResourceComparator.NAME);
        sortByTypeAction.setChecked(criteria == ResourceComparator.TYPE);
    }
}

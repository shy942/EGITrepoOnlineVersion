/***/
package org.eclipse.ui.views.navigator;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.navigator.ResourceNavigatorMessages;

/**
* Implementation of the view sorting actions.
* @since 2.0
* @deprecated as of 3.5, use the Common Navigator Framework classes instead
*/
@Deprecated
public class SortViewAction extends ResourceNavigatorAction {

    private int sortCriteria;

    /**
* Creates the action.
*
* @param navigator the resource navigator
* @param sortByType <code>true</code> for sort by type, <code>false</code> for sort by name
*/
    public  SortViewAction(IResourceNavigator navigator, boolean sortByType) {
        super(navigator, sortByType ? ResourceNavigatorMessages.SortView_byType : ResourceNavigatorMessages.SortView_byName);
        if (sortByType) {
            setToolTipText(ResourceNavigatorMessages.SortView_toolTipByType);
        } else {
            setToolTipText(ResourceNavigatorMessages.SortView_toolTipByName);
        }
        setEnabled(true);
        sortCriteria = sortByType ? ResourceComparator.TYPE : ResourceComparator.NAME;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, INavigatorHelpContextIds.SORT_VIEW_ACTION);
    }

    @Override
    public void run() {
        IResourceNavigator navigator = getNavigator();
        ResourceComparator comparator = navigator.getComparator();
        if (comparator == null) {
            navigator.setComparator(new ResourceComparator(sortCriteria));
        } else {
            comparator.setCriteria(sortCriteria);
            navigator.setComparator(comparator);
        }
    }
}

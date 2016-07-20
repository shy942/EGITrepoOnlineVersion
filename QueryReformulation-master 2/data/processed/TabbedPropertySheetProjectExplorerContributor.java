/***/
package org.eclipse.ui.internal.navigator.resources.workbench;

import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
* A tabbed property view contributor for the Project Explorer.
*
* @since 3.2
*/
public class TabbedPropertySheetProjectExplorerContributor implements ITabbedPropertySheetPageContributor {

    private final String contributorId;

    protected  TabbedPropertySheetProjectExplorerContributor(CommonNavigator aCommonNavigator) {
        contributorId = aCommonNavigator.getViewSite().getId();
    }

    @Override
    public String getContributorId() {
        return contributorId;
    }
}

/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.folders;

import org.eclipse.ui.tests.views.properties.tabbed.override.items.IOverrideTestsItem;

/**
* An item selection listener.
* <p>
* The OverrideTestsTabFolderPropertySheetPage example is a before look at the
* properties view before the migration to the tabbed properties view and the
* override tabs support. When elements are selected in the OverrideTestsView,
* TabFolder/TabItem are displayed for the elements.
*
* @author Anthony Hunter
* @since 3.4
*/
public interface IOverrideTestsTabItemSelectionListener {

    /**
* Notifies this listener that the selected item has changed.
*
* @param key
*            the name of the selected item.
*/
    public void itemSelected(IOverrideTestsItem item);
}

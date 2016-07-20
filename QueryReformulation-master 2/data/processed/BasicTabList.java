/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.tablist;

import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.Error;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;
import org.eclipse.ui.tests.views.properties.tabbed.model.Warning;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.ErrorItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.IOverrideTestsItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.InformationItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.WarningItem;

/**
* The basic tab list is displayed when Information, Warning or Error is the
* selected element in the override tests view.
* <p>
* The OverrideTestsView TabbedPropertySheetPage example is a look at the
* properties view after the migration of a TabFolder/TabItem framework to the
* tabbed properties view. In the case of a TabFolder, the folder (provider)
* knows both the tab labels and tab items. This aligns to the tabbed properties
* view, but the tab labels are tab descriptors and tab items are section
* descriptions. This does not work with the default framework as the tabs
* provide the sections. In this case, the IOverridableTabListContentProvider
* framework has been provided.
* <p>
* The overridable tab list is a content provider that provides both the
* sections and the tab labels.
*
* @author Anthony Hunter
* @since 3.4
*/
public class BasicTabList extends AbstractTabList {

    private IOverrideTestsItem[] overrideTestsItems;

    @Override
    public boolean appliesTo(Element element) {
        return ((element instanceof Information) || (element instanceof Warning) || (element instanceof Error));
    }

    @Override
    public IOverrideTestsItem[] getItems() {
        if (overrideTestsItems == null) {
            overrideTestsItems = new IOverrideTestsItem[] { new InformationItem(), new WarningItem(), new ErrorItem() };
        }
        return overrideTestsItems;
    }
}

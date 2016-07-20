/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.tablist;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.IOverrideTestsItem;
import org.eclipse.ui.views.properties.tabbed.ITabItem;

/**
* The abstract implementation of a tab list.
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
public abstract class AbstractTabList implements IOverrideTestsTabList {

    private Composite composite;

    private int selectedTabItem;

    @Override
    public boolean appliesTo(Element element) {
        return false;
    }

    @Override
    public void createControls(Composite parent) {
        this.composite = parent;
        OverrideTestsTabItem activeTab = (OverrideTestsTabItem) (getTabs()[selectedTabItem]);
        activeTab.getItem().createControls(parent);
    }

    @Override
    public void dispose() {
        OverrideTestsTabItem activeTab = (OverrideTestsTabItem) (getTabs()[selectedTabItem]);
        activeTab.getItem().dispose();
    }

    @Override
    public ITabItem[] getTabs() {
        IOverrideTestsItem[] items = getItems();
        OverrideTestsTabItem[] tabs = new OverrideTestsTabItem[items.length];
        for (int i = 0; i < items.length; i++) {
            tabs[i] = new OverrideTestsTabItem(items[i]);
            if (i == selectedTabItem) {
                tabs[i].setSelected(true);
            }
        }
        return tabs;
    }

    @Override
    public void selectionChanged(Element element) {
        ITabItem[] tabs = getTabs();
        for (int i = 0; i < tabs.length; i++) {
            if (tabs[i].getText().equals(element.getName())) {
                selectedTabItem = i;
                break;
            }
        }
    }

    @Override
    public void selectTab(int index) {
        if (selectedTabItem == index) {
            return;
        }
        OverrideTestsTabItem activeTab = (OverrideTestsTabItem) (getTabs()[selectedTabItem]);
        activeTab.getItem().dispose();
        selectedTabItem = index;
        activeTab = (OverrideTestsTabItem) (getTabs()[selectedTabItem]);
        activeTab.getItem().createControls(composite);
        composite.layout(true);
    }
}

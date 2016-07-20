/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.tablist;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
* Section descriptor for the override tests example.
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
public class OverrideTestsSectionDescriptor extends AbstractSectionDescriptor {

    private ISection section;

    @Override
    public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
        return true;
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "org.eclipse.ui.tests.views.properties.tabbed.override";
    }

    @Override
    public ISection getSectionClass() {
        if (section == null) {
            this.section = new OverrideTestsSection();
        }
        return section;
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "org.eclipse.ui.tests.views.properties.tabbed.override";
    }
}

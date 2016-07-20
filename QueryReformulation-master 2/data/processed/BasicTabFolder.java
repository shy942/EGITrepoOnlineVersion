/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.folders;

import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.Error;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;
import org.eclipse.ui.tests.views.properties.tabbed.model.Warning;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.ErrorItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.IOverrideTestsItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.InformationItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.WarningItem;

/**
* The basic TabFolder is displayed when Information, Warning or Error is the
* selected element in the override tests view.
* <p>
* The OverrideTestsTabFolderPropertySheetPage example is a before look at the
* properties view before the migration to the tabbed properties view and the
* override tabs support. When elements are selected in the OverrideTestsView,
* TabFolder/TabItem are displayed for the elements.
*
* @author Anthony Hunter
* @since 3.4
*/
public class BasicTabFolder extends AbstractTabFolder {

    @Override
    public boolean appliesTo(Element element) {
        return ((element instanceof Information) || (element instanceof Warning) || (element instanceof Error));
    }

    @Override
    public IOverrideTestsItem[] getItem() {
        return new IOverrideTestsItem[] { new InformationItem(), new WarningItem(), new ErrorItem() };
    }
}

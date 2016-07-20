/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.folders;

import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.File;
import org.eclipse.ui.tests.views.properties.tabbed.model.Folder;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.FileItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.FolderItem;
import org.eclipse.ui.tests.views.properties.tabbed.override.items.IOverrideTestsItem;

/**
* The advanced TabFolder is displayed when Information, Warning or Error is the
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
public class AdvancedTabFolder extends AbstractTabFolder {

    @Override
    public boolean appliesTo(Element element) {
        return ((element instanceof File) || (element instanceof Folder));
    }

    @Override
    public IOverrideTestsItem[] getItem() {
        return new IOverrideTestsItem[] { new FileItem(), new FolderItem() };
    }
}

/***/
package org.eclipse.ui.tests.views.properties.tabbed.decorations.views;

import org.eclipse.ui.tests.views.properties.tabbed.decorations.TabbedPropertySheetPageWithDecorations;
import org.eclipse.ui.tests.views.properties.tabbed.views.TestsView;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public class DecorationTestsView extends TestsView {

    //$NON-NLS-1$
    public static final String DECORATION_TESTS_VIEW_ID = "org.eclipse.ui.tests.views.properties.tabbed.decorations.views.DecorationTestsView";

    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class) {
            if (tabbedPropertySheetPage == null) {
                tabbedPropertySheetPage = new TabbedPropertySheetPageWithDecorations(this);
            }
            return tabbedPropertySheetPage;
        }
        return super.getAdapter(adapter);
    }
}

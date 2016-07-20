/***/
package org.eclipse.ui.tests.views.properties.tabbed.views;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.tests.views.properties.tabbed.decorations.views.DecorationTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.override.OverrideTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.text.TextTestsView;

/**
* Perspective used by the tabbed properties view test JUnit tests.
*
* @since 3.3
*/
public class TestsPerspective implements IPerspectiveFactory {

    //$NON-NLS-1$
    public static final String TESTS_PERSPECTIVE_ID = "org.eclipse.ui.tests.views.properties.tabbed.views.TestsPerspective";

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float) 0.50, //$NON-NLS-1$
        editorArea);
        topLeft.addView(TestsView.TESTS_VIEW_ID);
        IFolderLayout middleLeft = layout.createFolder("middleLeft", IPageLayout.BOTTOM, (float) 0.33, //$NON-NLS-1$
        "topLeft");
        middleLeft.addView(OverrideTestsView.OVERRIDE_TESTS_VIEW_ID);
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, (float) 0.40, //$NON-NLS-1$//$NON-NLS-2$
        "middleLeft");
        bottomLeft.addView(DynamicTestsView.DYNAMIC_TESTS_VIEW_ID);
        IFolderLayout top = layout.createFolder("top", IPageLayout.TOP, (float) 0.25, //$NON-NLS-1$
        editorArea);
        top.addView(TextTestsView.TEXT_TESTS_VIEW_ID);
        top.addPlaceholder(DecorationTestsView.DECORATION_TESTS_VIEW_ID);
        IFolderLayout bottom = layout.createFolder(//$NON-NLS-1$
        "bottom", //$NON-NLS-1$
        IPageLayout.BOTTOM, //$NON-NLS-1$
        (float) 0.25, editorArea);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
    }
}

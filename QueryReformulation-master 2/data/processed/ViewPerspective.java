/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
* Supplies an arrangement of views to test various forms of view activation.
*
* @since 3.0
*/
public class ViewPerspective implements IPerspectiveFactory {

    public static final String ID = "org.eclipse.ui.tests.api.ViewPerspective";

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        IFolderLayout folder1 = layout.createFolder("folder1", IPageLayout.LEFT, .25f, editorArea);
        folder1.addView(MockViewPart.ID);
        folder1.addPlaceholder(MockViewPart.ID2);
        layout.addView(MockViewPart.ID3, IPageLayout.RIGHT, .75f, editorArea);
        layout.addPlaceholder(MockViewPart.ID4, IPageLayout.BOTTOM, .75f, editorArea);
    }
}

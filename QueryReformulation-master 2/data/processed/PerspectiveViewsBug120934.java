/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
* A layout that can trigger an problem interaction between
* {@link org.eclipse.ui.internal.presentations.util.PresentablePartFolder}
* and {@link org.eclipse.ui.internal.presentations.defaultpresentation.EmptyTabFolder}.
*
* @since 3.2
*/
public class PerspectiveViewsBug120934 implements IPerspectiveFactory {

    public static final String PERSP_ID = "org.eclipse.ui.tests.api.PerspectiveViewsBug120934";

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.addView(IPageLayout.ID_BOOKMARKS, IPageLayout.RIGHT, 0.25f, IPageLayout.ID_EDITOR_AREA);
        layout.addStandaloneView(IPageLayout.ID_OUTLINE, false, IPageLayout.TOP, .3f, IPageLayout.ID_BOOKMARKS);
        layout.setEditorAreaVisible(false);
    }
}

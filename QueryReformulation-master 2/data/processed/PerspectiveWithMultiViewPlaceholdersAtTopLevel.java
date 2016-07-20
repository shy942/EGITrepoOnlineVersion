/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
* This perspective is used for testing api. It defines an initial layout with
* placeholders for multi-instance views, including wildcards.
* The placeholders are added at top level (not in any folder).
*
* @since 3.1
*/
public class PerspectiveWithMultiViewPlaceholdersAtTopLevel implements IPerspectiveFactory {

    //$NON-NLS-1$
    public static String PERSP_ID = "org.eclipse.ui.tests.PerspectiveWithMultiViewPlaceholdersAtTopLevel";

    public  PerspectiveWithMultiViewPlaceholdersAtTopLevel() {
    // do nothing
    }

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.addPlaceholder("*", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
        layout.addPlaceholder(MockViewPart.IDMULT, IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
        layout.addPlaceholder(MockViewPart.IDMULT + ":secondaryId", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
        layout.addPlaceholder(MockViewPart.IDMULT + ":*", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
    }
}

/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
* This perspective is used for testing api. It defines an initial layout with
* placeholders for some views, to be used to test closing fast views. The
* placeholders are added at top level (not in any folder).
*
* @since 3.1.1
*/
public class PerspectiveViewsBug88345 implements IPerspectiveFactory {

    public static final String NORMAL_VIEW_ID = "org.eclipse.ui.views.ContentOutline";

    public static final String PERSP_ID = "org.eclipse.ui.tests.api.PerspectiveViewsBug88345";

    public static final String MOVE_ID = MockViewPart.IDMULT + ":1";

    /**
* A view with a toolbar (not a room with a view).
*/
    public static final String PROP_SHEET_ID = "org.eclipse.ui.views.PropertySheet";

    public  PerspectiveViewsBug88345() {
    // do nothing
    }

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.addView(MockViewPart.IDMULT, IPageLayout.LEFT, 0.33f, IPageLayout.ID_EDITOR_AREA);
        layout.addView(NORMAL_VIEW_ID, IPageLayout.RIGHT, 0.25f, IPageLayout.ID_EDITOR_AREA);
        layout.addView(PROP_SHEET_ID, IPageLayout.RIGHT, 0.75f, NORMAL_VIEW_ID);
        layout.getViewLayout(MockViewPart.IDMULT).setCloseable(false);
        layout.getViewLayout(MOVE_ID).setMoveable(false);
    }
}

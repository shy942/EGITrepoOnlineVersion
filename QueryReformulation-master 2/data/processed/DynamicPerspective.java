/***/
package org.eclipse.ui.dynamic;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
* @since 3.1
*/
public class DynamicPerspective implements IPerspectiveFactory {

    /**
*
*/
    public  DynamicPerspective() {
        super();
    }

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, .25f, IPageLayout.ID_EDITOR_AREA);
    }
}

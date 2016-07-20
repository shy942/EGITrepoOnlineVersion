/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

/**
* This perspective is used for testing api. It defines an initial layout with
* placeholders for multi-instance views, including wildcards.
* The placeholders are added in a placeholder folder.
*
* @since 3.1
*/
public class PerspectiveWithMultiViewPlaceholdersInPlaceholderFolder implements IPerspectiveFactory {

    //$NON-NLS-1$
    public static String PERSP_ID = "org.eclipse.ui.tests.PerspectiveWithMultiViewPlaceholdersInPlaceholderFolder";

    public  PerspectiveWithMultiViewPlaceholdersInPlaceholderFolder() {
    // do nothing
    }

    @Override
    public void createInitialLayout(IPageLayout layout) {
        IPlaceholderFolderLayout folder = layout.createPlaceholderFolder("placeholderFolder", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
        addPlaceholders(folder);
    }

    protected void addPlaceholders(IPlaceholderFolderLayout folder) {
        folder.addPlaceholder("*");
        folder.addPlaceholder(MockViewPart.IDMULT);
        folder.addPlaceholder(MockViewPart.IDMULT + ":secondaryId");
        folder.addPlaceholder(MockViewPart.IDMULT + ":*");
    }
}

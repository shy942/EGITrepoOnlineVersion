/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
* This perspective is used for testing api. It defines an initial layout with
* placeholders for multi-instance views, including wildcards.
* The placeholders are added in a folder (not a placeholder folder).
*
* @since 3.1
*/
public class PerspectiveWithMultiViewPlaceholdersInFolder extends PerspectiveWithMultiViewPlaceholdersInPlaceholderFolder {

    //$NON-NLS-1$
    public static String PERSP_ID = "org.eclipse.ui.tests.PerspectiveWithMultiViewPlaceholdersInFolder";

    public  PerspectiveWithMultiViewPlaceholdersInFolder() {
    // do nothing
    }

    @Override
    public void createInitialLayout(IPageLayout layout) {
        IFolderLayout folder = layout.createFolder("folder", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
        addPlaceholders(folder);
    }
}

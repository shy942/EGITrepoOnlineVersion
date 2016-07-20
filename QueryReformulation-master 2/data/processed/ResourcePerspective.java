/***/
package org.eclipse.ui.internal.ide.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewFolderResourceWizard;

/**
*/
public class ResourcePerspective implements IPerspectiveFactory {

    /**
* Constructs a new Default layout engine.
*/
    public  ResourcePerspective() {
        super();
    }

    /**
* Defines the initial layout for a perspective.
*
* Implementors of this method may add additional views to a
* perspective.  The perspective already contains an editor folder
* with <code>ID = ILayoutFactory.ID_EDITORS</code>.  Add additional views
* to the perspective in reference to the editor folder.
*
* This method is only called when a new perspective is created.  If
* an old perspective is restored from a persistence file then
* this method is not called.
*
* @param layout the factory used to add views to the perspective
*/
    @Override
    public void createInitialLayout(IPageLayout layout) {
        defineActions(layout);
        defineLayout(layout);
    }

    /**
* Defines the initial actions for a page.
* @param layout The layout we are filling
*/
    public void defineActions(IPageLayout layout) {
        // Add "new wizards".
        layout.addNewWizardShortcut(BasicNewFolderResourceWizard.WIZARD_ID);
        layout.addNewWizardShortcut(BasicNewFileResourceWizard.WIZARD_ID);
        // Add "show views".
        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
        layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    /**
* Defines the initial layout for a page.
* @param layout The layout we are filling
*/
    public void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();
        // Top left.
        IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float) 0.26, //$NON-NLS-1$
        editorArea);
        topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
        topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);
        // Add a placeholder for the old navigator to maintain compatibility
        //$NON-NLS-1$
        topLeft.addPlaceholder("org.eclipse.ui.views.ResourceNavigator");
        // Bottom left.
        IFolderLayout bottomLeft = layout.createFolder(//$NON-NLS-1$
        "bottomLeft", //$NON-NLS-1$
        IPageLayout.BOTTOM, //$NON-NLS-1$
        (float) 0.50, //$NON-NLS-1$
        "topLeft");
        bottomLeft.addView(IPageLayout.ID_OUTLINE);
        // Bottom right.
        IFolderLayout bottomRight = layout.createFolder(//$NON-NLS-1$
        "bottomRight", //$NON-NLS-1$
        IPageLayout.BOTTOM, //$NON-NLS-1$
        (float) 0.66, editorArea);
        bottomRight.addView(IPageLayout.ID_TASK_LIST);
    }
}

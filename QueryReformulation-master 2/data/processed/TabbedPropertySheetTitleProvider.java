/***/
package org.eclipse.ui.internal.navigator.resources.workbench;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.resources.plugin.WorkbenchNavigatorPlugin;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

/**
* Defines a label provider for the title bar in the tabbed properties view.
*
* @since 3.2
*/
public class TabbedPropertySheetTitleProvider extends LabelProvider {

    private ILabelProvider labelProvider;

    private IDescriptionProvider descriptionProvider;

    /**
* Constructor for CommonNavigatorTitleProvider.
*/
    public  TabbedPropertySheetTitleProvider() {
        super();
        INavigatorContentService contentService = null;
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            IWorkbenchPart part = window.getActivePage().findView(ProjectExplorer.VIEW_ID);
            if (part != null) {
                contentService = Adapters.adapt(part, INavigatorContentService.class);
                if (contentService != null) {
                    labelProvider = contentService.createCommonLabelProvider();
                    descriptionProvider = contentService.createCommonDescriptionProvider();
                } else {
                    WorkbenchNavigatorPlugin.log(//$NON-NLS-1$ //$NON-NLS-2$
                    "Could not acquire INavigatorContentService from part (\"" + part.getTitle() + "\").", null);
                }
            } else {
                //$NON-NLS-1$
                WorkbenchNavigatorPlugin.log(//$NON-NLS-1$
                "Could not acquire INavigatorContentService: Project Explorer not found.", null);
            }
        } else {
            //$NON-NLS-1$
            WorkbenchNavigatorPlugin.log("Could not acquire INavigatorContentService: no active window.", null);
        }
    }

    /**
* @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
*/
    @Override
    public Image getImage(Object object) {
        return labelProvider != null ? labelProvider.getImage(object) : null;
    }

    /**
* @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
*/
    @Override
    public String getText(Object object) {
        return descriptionProvider != null ? descriptionProvider.getDescription(object) : null;
    }
}

/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.items;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.tests.views.properties.tabbed.model.Warning;

/**
* An item for when the Warning element is the selected element in the override
* tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class WarningItem extends InformationItem {

    @Override
    public Class getElement() {
        return Warning.class;
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
    }

    @Override
    public String getText() {
        //$NON-NLS-1$
        return "Warning";
    }
}

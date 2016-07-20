/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.items;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.tests.views.properties.tabbed.model.Error;

/**
* An item for when the Error element is the selected element in the override
* tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class ErrorItem extends InformationItem {

    @Override
    public Class getElement() {
        return Error.class;
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
    }

    @Override
    public String getText() {
        //$NON-NLS-1$
        return "Error";
    }
}

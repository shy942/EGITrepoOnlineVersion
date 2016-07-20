/***/
package org.eclipse.ui.internal.quickaccess;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.internal.WorkbenchImages;

/**
* @since 3.3
*
*/
public class PerspectiveProvider extends QuickAccessProvider {

    private QuickAccessElement[] cachedElements;

    private Map<String, PerspectiveElement> idToElement = new HashMap();

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "org.eclipse.ui.perspectives";
    }

    @Override
    public QuickAccessElement getElementForId(String id) {
        getElements();
        return idToElement.get(id);
    }

    @Override
    public QuickAccessElement[] getElements() {
        if (cachedElements == null) {
            IPerspectiveDescriptor[] perspectives = PlatformUI.getWorkbench().getPerspectiveRegistry().getPerspectives();
            cachedElements = new QuickAccessElement[perspectives.length];
            for (int i = 0; i < perspectives.length; i++) {
                if (!WorkbenchActivityHelper.filterItem(perspectives[i])) {
                    PerspectiveElement perspectiveElement = new PerspectiveElement(perspectives[i], this);
                    idToElement.put(perspectiveElement.getId(), perspectiveElement);
                }
            }
            cachedElements = idToElement.values().toArray(new QuickAccessElement[idToElement.size()]);
        }
        return cachedElements;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return WorkbenchImages.getImageDescriptor(ISharedImages.IMG_ETOOL_DEF_PERSPECTIVE);
    }

    @Override
    public String getName() {
        return QuickAccessMessages.QuickAccess_Perspectives;
    }

    @Override
    protected void doReset() {
        cachedElements = null;
        idToElement.clear();
    }
}

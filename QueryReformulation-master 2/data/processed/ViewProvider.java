/***/
package org.eclipse.ui.internal.quickaccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.e4.compatibility.CompatibilityEditor;
import org.eclipse.ui.internal.e4.compatibility.CompatibilityPart;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

/**
* @since 3.3
*
*/
public class ViewProvider extends QuickAccessProvider {

    private MApplication application;

    private MWindow window;

    private Map<String, QuickAccessElement> idToElement = new HashMap();

    private IViewRegistry viewRegistry;

    public  ViewProvider(MApplication application, MWindow window) {
        this.application = application;
        this.window = window;
        this.viewRegistry = PlatformUI.getWorkbench().getViewRegistry();
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "org.eclipse.e4.ui.parts";
    }

    @Override
    public QuickAccessElement getElementForId(String id) {
        getElements();
        return idToElement.get(id);
    }

    @Override
    public QuickAccessElement[] getElements() {
        IWorkbenchWindow workbenchWindow = window.getContext().get(IWorkbenchWindow.class);
        if (workbenchWindow == null || workbenchWindow.getActivePage() == null) {
            return new QuickAccessElement[0];
        }
        if (idToElement.isEmpty()) {
            List<MPartDescriptor> descriptors = application.getDescriptors();
            for (int i = 0; i < descriptors.size(); i++) {
                MPartDescriptor descriptor = descriptors.get(i);
                String uri = descriptor.getContributionURI();
                if (uri != null) {
                    String id = descriptor.getElementId();
                    if (id != null && !id.equals(CompatibilityEditor.MODEL_ELEMENT_ID)) {
                        ViewElement element = new ViewElement(this, window, descriptor);
                        if (uri.equals(CompatibilityPart.COMPATIBILITY_VIEW_URI)) {
                            IViewDescriptor viewDescriptor = viewRegistry.find(element.getId());
                            // Ignore if restricted
                            if (viewDescriptor == null)
                                continue;
                            // Ignore if filtered
                            if (!WorkbenchActivityHelper.filterItem(viewDescriptor)) {
                                idToElement.put(element.getId(), element);
                            }
                        } else {
                            idToElement.put(id, element);
                        }
                    }
                }
            }
        }
        return idToElement.values().toArray(new QuickAccessElement[idToElement.size()]);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_VIEW_DEFAULTVIEW_MISC);
    }

    @Override
    public String getName() {
        return QuickAccessMessages.QuickAccess_Views;
    }

    @Override
    protected void doReset() {
        idToElement.clear();
    }
}

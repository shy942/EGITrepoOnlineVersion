/***/
package org.eclipse.ui.internal.intro;

import java.util.ArrayList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.registry.RegistryReader;

/**
* Registry for introduction elements.
*
* @since 3.0
*/
public class IntroRegistry implements IIntroRegistry {

    //$NON-NLS-1$
    private static final String TAG_INTRO = "intro";

    //$NON-NLS-1$
    private static final String TAG_INTROPRODUCTBINDING = "introProductBinding";

    //$NON-NLS-1$
    private static final String ATT_INTROID = "introId";

    //$NON-NLS-1$
    private static final String ATT_PRODUCTID = "productId";

    @Override
    public int getIntroCount() {
        return getIntros().length;
    }

    @Override
    public IIntroDescriptor[] getIntros() {
        IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(PlatformUI.PLUGIN_ID, IWorkbenchRegistryConstants.PL_INTRO);
        if (point == null) {
            return new IIntroDescriptor[0];
        }
        IExtension[] extensions = point.getExtensions();
        extensions = RegistryReader.orderExtensions(extensions);
        ArrayList list = new ArrayList(extensions.length);
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++) {
                if (elements[j].getName().equals(TAG_INTRO)) {
                    try {
                        IIntroDescriptor descriptor = new IntroDescriptor(elements[j]);
                        list.add(descriptor);
                    } catch (CoreException e) {
                        WorkbenchPlugin.log(IntroMessages.Intro_could_not_create_descriptor, e.getStatus());
                    }
                }
            }
        }
        return (IIntroDescriptor[]) list.toArray(new IIntroDescriptor[list.size()]);
    }

    @Override
    public IIntroDescriptor getIntroForProduct(String targetProductId) {
        IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(PlatformUI.PLUGIN_ID, IWorkbenchRegistryConstants.PL_INTRO);
        if (point == null) {
            return null;
        }
        IExtension[] extensions = point.getExtensions();
        extensions = RegistryReader.orderExtensions(extensions);
        String targetIntroId = getIntroForProduct(targetProductId, extensions);
        if (targetIntroId == null) {
            return null;
        }
        IIntroDescriptor descriptor = null;
        IIntroDescriptor[] intros = getIntros();
        for (int i = 0; i < intros.length; i++) {
            if (intros[i].getId().equals(targetIntroId)) {
                descriptor = intros[i];
                break;
            }
        }
        return descriptor;
    }

    /**
* @param targetProductId
* @param extensions
* @return
*/
    private String getIntroForProduct(String targetProductId, IExtension[] extensions) {
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++) {
                if (elements[j].getName().equals(TAG_INTROPRODUCTBINDING)) {
                    String introId = elements[j].getAttribute(ATT_INTROID);
                    String productId = elements[j].getAttribute(ATT_PRODUCTID);
                    if (introId == null || productId == null) {
                        IStatus status = new Status(IStatus.ERROR, elements[j].getDeclaringExtension().getNamespace(), IStatus.ERROR, "introId and productId must be defined.", //$NON-NLS-1$
                        new IllegalArgumentException());
                        //$NON-NLS-1$
                        WorkbenchPlugin.log("Invalid intro binding", status);
                        continue;
                    }
                    if (targetProductId.equals(productId)) {
                        return introId;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public IIntroDescriptor getIntro(String id) {
        IIntroDescriptor[] intros = getIntros();
        for (int i = 0; i < intros.length; i++) {
            IIntroDescriptor desc = intros[i];
            if (desc.getId().equals(id)) {
                return desc;
            }
        }
        return null;
    }
}

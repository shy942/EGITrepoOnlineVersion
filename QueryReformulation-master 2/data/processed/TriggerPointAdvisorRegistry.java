/***/
package org.eclipse.ui.internal.activities.ws;

import java.util.ArrayList;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.misc.StatusUtil;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.registry.RegistryReader;

/**
* @since 3.1
*/
public class TriggerPointAdvisorRegistry {

    private static TriggerPointAdvisorRegistry instance;

    /**
*
*/
    private  TriggerPointAdvisorRegistry() {
    }

    /**
* Return the instance of this registry.
*
* @return the instance of this registry
*/
    public static TriggerPointAdvisorRegistry getInstance() {
        if (instance == null) {
            instance = new TriggerPointAdvisorRegistry();
        }
        return instance;
    }

    /**
* Return the trigger point advisors.
*
* @return the advisors
*/
    public TriggerPointAdvisorDescriptor[] getAdvisors() {
        IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(PlatformUI.PLUGIN_ID, IWorkbenchRegistryConstants.PL_ACTIVITYSUPPORT);
        if (point == null) {
            return new TriggerPointAdvisorDescriptor[0];
        }
        IExtension[] extensions = point.getExtensions();
        extensions = RegistryReader.orderExtensions(extensions);
        ArrayList list = new ArrayList(extensions.length);
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++) {
                if (elements[j].getName().equals(IWorkbenchRegistryConstants.TAG_TRIGGERPOINTADVISOR)) {
                    try {
                        TriggerPointAdvisorDescriptor descriptor = new TriggerPointAdvisorDescriptor(elements[j]);
                        list.add(descriptor);
                    } catch (IllegalArgumentException e) {
                        WorkbenchPlugin.log("invalid trigger point advisor extension", StatusUtil.newStatus(IStatus.ERROR, e.getMessage(), e));
                    }
                }
            }
        }
        return (TriggerPointAdvisorDescriptor[]) list.toArray(new TriggerPointAdvisorDescriptor[list.size()]);
    }

    /**
* Return the trigger point advisor bound to a given product.
*
* @param productId
*            the product id
* @return the advisor
*/
    public TriggerPointAdvisorDescriptor getAdvisorForProduct(String productId) {
        IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(PlatformUI.PLUGIN_ID, IWorkbenchRegistryConstants.PL_ACTIVITYSUPPORT);
        if (point == null) {
            return null;
        }
        IExtension[] extensions = point.getExtensions();
        extensions = RegistryReader.orderExtensions(extensions);
        String targetIntroId = getAdvisorForProduct(productId, extensions);
        if (targetIntroId == null) {
            return null;
        }
        TriggerPointAdvisorDescriptor[] advisors = getAdvisors();
        for (int i = 0; i < advisors.length; i++) {
            if (advisors[i].getId().equals(targetIntroId)) {
                return advisors[i];
            }
        }
        return null;
    }

    /**
* @param targetProductId
* @param extensions
* @return the advisor id
*/
    private String getAdvisorForProduct(String targetProductId, IExtension[] extensions) {
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++) {
                if (elements[j].getName().equals(IWorkbenchRegistryConstants.TAG_ADVISORPRODUCTBINDING)) {
                    String advisorId = elements[j].getAttribute(IWorkbenchRegistryConstants.ATT_ADVISORID);
                    String productId = elements[j].getAttribute(IWorkbenchRegistryConstants.ATT_PRODUCTID);
                    if (advisorId == null || productId == null) {
                        IStatus status = new Status(IStatus.ERROR, elements[j].getDeclaringExtension().getNamespace(), IStatus.ERROR, "triggerPointAdvisorId and productId must be defined.", //$NON-NLS-1$
                        new IllegalArgumentException());
                        WorkbenchPlugin.log("Invalid trigger point advisor binding", //$NON-NLS-1$
                        status);
                        continue;
                    }
                    if (targetProductId.equals(productId)) {
                        return advisorId;
                    }
                }
            }
        }
        return null;
    }
}

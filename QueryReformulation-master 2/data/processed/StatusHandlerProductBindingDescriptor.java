/***/
package org.eclipse.ui.internal.statushandlers;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* The status handler product binding descriptor.
*
* @since 3.3
*/
class StatusHandlerProductBindingDescriptor implements IPluginContribution {

    /**
* Handler id attribute. Value <code>handlerId</code>.
*/
    //$NON-NLS-1$
    private static String ATT_HANDLER_ID = "handlerId";

    private String id;

    private String pluginId;

    private String productId;

    private String handlerId;

    /**
* @param configElement
*/
    public  StatusHandlerProductBindingDescriptor(IConfigurationElement configElement) {
        super();
        id = configElement.getAttribute(IWorkbenchRegistryConstants.ATT_ID);
        pluginId = configElement.getContributor().getName();
        productId = configElement.getAttribute(IWorkbenchRegistryConstants.ATT_PRODUCTID);
        handlerId = configElement.getAttribute(ATT_HANDLER_ID);
    }

    @Override
    public String getLocalId() {
        return id;
    }

    @Override
    public String getPluginId() {
        return pluginId;
    }

    /**
* @return Returns the productId.
*/
    public String getProductId() {
        return productId;
    }

    /**
* @return Returns the handlerId.
*/
    public String getHandlerId() {
        return handlerId;
    }
}

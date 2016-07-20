/***/
package org.eclipse.ui.internal.activities.ws;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.activities.Persistence;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* @since 3.1
*/
public class RegistryTriggerPoint extends AbstractTriggerPoint {

    private String id;

    private IConfigurationElement element;

    private Map hints;

    /**
* Create a new instance of this class.
*
* @param id the id of the trigger point
* @param element the defining configuration element
*/
    public  RegistryTriggerPoint(String id, IConfigurationElement element) {
        this.id = id;
        this.element = element;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getStringHint(String key) {
        return (String) getHints().get(key);
    }

    @Override
    public boolean getBooleanHint(String key) {
        return Boolean.valueOf(getStringHint(key)).booleanValue();
    }

    /**
* Lazily create the hints.
*
* @return the hint map
*/
    private Map getHints() {
        if (hints == null) {
            hints = new HashMap();
            IConfigurationElement[] hintElements = element.getChildren(IWorkbenchRegistryConstants.TAG_HINT);
            for (int i = 0; i < hintElements.length; i++) {
                String id = hintElements[i].getAttribute(IWorkbenchRegistryConstants.ATT_ID);
                String value = hintElements[i].getAttribute(IWorkbenchRegistryConstants.ATT_VALUE);
                if (id == null || value == null) {
                    //$NON-NLS-1$
                    Persistence.log(element, Persistence.ACTIVITY_TRIGGER_HINT_DESC, "hint must contain ID and value");
                    continue;
                }
                hints.put(id, value);
            }
        }
        return hints;
    }
}

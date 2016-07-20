/***/
package org.eclipse.ui.internal.registry;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.ui.PlatformUI;

/**
* Contains extensions defined on the <code>keywords</code> extension point.
*
* @since 3.1
*/
public final class KeywordRegistry implements IExtensionChangeHandler {

    //$NON-NLS-1$
    private static final String ATT_ID = "id";

    //$NON-NLS-1$
    private static final String ATT_LABEL = "label";

    private static KeywordRegistry instance;

    //$NON-NLS-1$
    private static final String TAG_KEYWORD = "keyword";

    /**
* Return the singleton instance of the <code>KeywordRegistry</code>.
*
* @return the singleton registry
*/
    public static KeywordRegistry getInstance() {
        if (instance == null) {
            instance = new KeywordRegistry();
        }
        return instance;
    }

    /**
* Map of id->labels.
*/
    private Map internalKeywordMap = new HashMap();

    /**
* Private constructor.
*/
    private  KeywordRegistry() {
        IExtensionTracker tracker = PlatformUI.getWorkbench().getExtensionTracker();
        tracker.registerHandler(this, ExtensionTracker.createExtensionPointFilter(getExtensionPointFilter()));
        IExtension[] extensions = getExtensionPointFilter().getExtensions();
        for (int i = 0; i < extensions.length; i++) {
            addExtension(PlatformUI.getWorkbench().getExtensionTracker(), extensions[i]);
        }
    }

    @Override
    public void addExtension(IExtensionTracker tracker, IExtension extension) {
        IConfigurationElement[] elements = extension.getConfigurationElements();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getName().equals(TAG_KEYWORD)) {
                String name = elements[i].getAttribute(ATT_LABEL);
                String id = elements[i].getAttribute(ATT_ID);
                internalKeywordMap.put(id, name);
                PlatformUI.getWorkbench().getExtensionTracker().registerObject(extension, id, IExtensionTracker.REF_WEAK);
            }
        }
    }

    private IExtensionPoint getExtensionPointFilter() {
        return Platform.getExtensionRegistry().getExtensionPoint(PlatformUI.PLUGIN_ID, IWorkbenchRegistryConstants.PL_KEYWORDS);
    }

    /**
* Return the label associated with the given keyword.
*
* @param id the keyword id
* @return the label or <code>null</code>
*/
    public String getKeywordLabel(String id) {
        return (String) internalKeywordMap.get(id);
    }

    @Override
    public void removeExtension(IExtension extension, Object[] objects) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof String) {
                internalKeywordMap.remove(objects[i]);
            }
        }
    }
}
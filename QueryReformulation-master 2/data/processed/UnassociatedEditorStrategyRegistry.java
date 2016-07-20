/***/
package org.eclipse.ui.internal.ide;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.ide.IUnassociatedEditorStrategy;

/**
* @since 3.12
*
*/
public class UnassociatedEditorStrategyRegistry {

    //$NON-NLS-1$
    private static final String EXTENSION_POINT_ID = IDEWorkbenchPlugin.IDE_WORKBENCH + ".unassociatedEditorStrategy";

    private static Map<String, String> idsToLabel;

    /**
* @param strategyId
*            The strategy to look for
* @return an instance of the strategy, or {@code null} if no strategy is
*         found for this id
*/
    public IUnassociatedEditorStrategy getStrategy(String strategyId) {
        if (strategyId == null) {
            return null;
        }
        IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
        IConfigurationElement[] extensions = extRegistry.getConfigurationElementsFor(EXTENSION_POINT_ID);
        if (extensions != null) {
            for (IConfigurationElement extension : extensions) {
                if (strategyId.equals(readAttribute(extension, "id"))) {
                    //$NON-NLS-1$
                    try {
                        //$NON-NLS-1$
                        return (IUnassociatedEditorStrategy) extension.createExecutableExtension("class");
                    } catch (CoreException ex) {
                        IDEWorkbenchPlugin.log(ex.getMessage(), ex);
                        return null;
                    }
                }
            }
        }
        return null;
    }

    private String readAttribute(IConfigurationElement extension, String attribute) {
        String res = extension.getAttribute(attribute);
        if (res == null) {
            IDEWorkbenchPlugin.log(//$NON-NLS-1$ //$NON-NLS-2$
            "Missing attribute '" + attribute + "' for extension to " + EXTENSION_POINT_ID + " contributed by " + //$NON-NLS-1$
            extension.getContributor());
        }
        return res;
    }

    /**
* Reads the known strategies from registry
*
* @return not modifiable set with all known strategy id's in their
*         definition order
*/
    public Set<String> retrieveAllStrategies() {
        populateIdsToLabel();
        return Collections.unmodifiableSet(idsToLabel.keySet());
    }

    /**
* @param id
*            the id of the strategy to use
* @return the label for the supplied strategy id, or {@code null} for
*         unknown id.
*/
    public String getLabel(String id) {
        if (idsToLabel == null || !idsToLabel.containsKey(id)) {
            populateIdsToLabel();
        }
        return idsToLabel.get(id);
    }

    private void populateIdsToLabel() {
        Map<String, String> res = new LinkedHashMap();
        IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
        IConfigurationElement[] extensions = extRegistry.getConfigurationElementsFor(EXTENSION_POINT_ID);
        if (extensions != null) {
            for (IConfigurationElement extension : extensions) {
                //$NON-NLS-1$
                String extId = readAttribute(extension, "id");
                //$NON-NLS-1$
                String label = readAttribute(extension, "label");
                if (extId != null && label != null) {
                    res.put(extId, label);
                }
            }
        }
        idsToLabel = res;
    }

    /**
* @param strategyId
* @return Whether the specified strategy is interactive, or false is
*         strategy is unknown
*/
    public boolean isInteractive(String strategyId) {
        if (strategyId == null) {
            return false;
        }
        IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
        IConfigurationElement[] extensions = extRegistry.getConfigurationElementsFor(EXTENSION_POINT_ID);
        if (extensions != null) {
            for (IConfigurationElement extension : extensions) {
                if (strategyId.equals(readAttribute(extension, "id"))) {
                    //$NON-NLS-1$
                    return Boolean.parseBoolean(readAttribute(extension, "interactive"));
                }
            }
        }
        //$NON-NLS-1$
        IDEWorkbenchPlugin.log("No editor strategy found for " + strategyId);
        return false;
    }
}

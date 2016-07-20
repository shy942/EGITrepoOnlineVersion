/***/
package org.eclipse.ui.internal.menus;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.IConfigurationElement;

/**
* A registry between a given string id and a configuration element that
* corresponds to a control contribution.
*/
public class ControlContributionRegistry {

    private static Map<String, IConfigurationElement> registry = new HashMap();

    public static void clear() {
        registry.clear();
    }

    public static void add(String id, IConfigurationElement element) {
        registry.put(id, element);
    }

    public static IConfigurationElement get(String id) {
        return registry.get(id);
    }
}

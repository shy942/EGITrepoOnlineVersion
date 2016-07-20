/***/
package org.eclipse.ui.tests.services;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

/**
* A registered source provider that can update variables for the
* IEvaluationService.
*
* @since 3.4
*/
public class ActiveUserSourceProvider extends AbstractSourceProvider {

    private static final String[] PROVIDED_SOURCE_NAMES = new String[] { "username" };

    private String username = "guest";

    @Override
    public void dispose() {
    }

    @Override
    public Map getCurrentState() {
        Map map = new HashMap();
        map.put(PROVIDED_SOURCE_NAMES[0], username);
        return map;
    }

    public void setUsername(String name) {
        username = name;
        fireSourceChanged(ISources.ACTIVE_CONTEXT << 1, PROVIDED_SOURCE_NAMES[0], name);
    }

    @Override
    public String[] getProvidedSourceNames() {
        return PROVIDED_SOURCE_NAMES;
    }
}

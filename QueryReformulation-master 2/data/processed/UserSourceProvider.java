/***/
package org.eclipse.ui.examples.contributions.model;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

/**
* An example of provide a Person as a variable to the evaluation service.
*
* @since 3.4
*/
public class UserSourceProvider extends AbstractSourceProvider {

    //$NON-NLS-1$
    public static final String USER = "org.eclipse.ui.examples.contributions.user";

    private static final String[] PROVIDED_SOURCE_NAMES = new String[] { USER };

    private static final Object GUEST = new Object();

    private Person user = null;

    @Override
    public void dispose() {
        user = null;
    }

    @Override
    public Map<?, ?> getCurrentState() {
        Map<String, Object> m = new HashMap();
        m.put(USER, getCurrentUser());
        return m;
    }

    private Object getCurrentUser() {
        return user == null ? GUEST : user;
    }

    public void login(Person person) {
        user = person;
        // I'm not sure whether this has to be accurate, so use the matching
        // declaration priority <<1 for now
        fireSourceChanged(ISources.ACTIVE_SITE << 1, USER, getCurrentUser());
    }

    @Override
    public String[] getProvidedSourceNames() {
        return PROVIDED_SOURCE_NAMES;
    }
}

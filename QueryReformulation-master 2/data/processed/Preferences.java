/***/
package org.eclipse.e4.ui.progress.internal;

import java.util.Map;
import javax.inject.Inject;
import org.eclipse.e4.ui.model.application.MApplication;

public class Preferences {

    private static Map<String, String> preferences;

    @Inject
    private static synchronized void updatePreferences(MApplication application) {
        preferences = application.getPersistedState();
    }

    public static synchronized boolean getBoolean(String key) {
        return Boolean.parseBoolean(preferences.get(key));
    }

    public static synchronized void set(String key, boolean value) {
        preferences.put(key, Boolean.toString(value));
    }

    public static synchronized void set(String key, String value) {
        preferences.put(key, value);
    }
}

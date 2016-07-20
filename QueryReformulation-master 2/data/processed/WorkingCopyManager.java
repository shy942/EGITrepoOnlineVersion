/***/
package org.eclipse.ui.preferences;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.ui.internal.preferences.WorkingCopyPreferences;
import org.osgi.service.prefs.BackingStoreException;

/**
* WorkingCopyManager is a concrete implementation of an
* IWorkingCopyManager.
* <p>
* This class is not intended to be sub-classed by clients.
* </p>
* @since 3.2
*/
public class WorkingCopyManager implements IWorkingCopyManager {

    //$NON-NLS-1$
    private static final String EMPTY_STRING = "";

    // all working copies - maps absolute path to PreferencesWorkingCopy instance
    private Map workingCopies = new HashMap();

    @Override
    public IEclipsePreferences getWorkingCopy(IEclipsePreferences original) {
        if (original instanceof WorkingCopyPreferences) {
            //$NON-NLS-1$
            throw new IllegalArgumentException("Trying to get a working copy of a working copy");
        }
        String absolutePath = original.absolutePath();
        IEclipsePreferences preferences = (IEclipsePreferences) workingCopies.get(absolutePath);
        if (preferences == null) {
            preferences = new WorkingCopyPreferences(original, this);
            workingCopies.put(absolutePath, preferences);
        }
        return preferences;
    }

    @Override
    public void applyChanges() throws BackingStoreException {
        Collection values = workingCopies.values();
        WorkingCopyPreferences[] valuesArray = (WorkingCopyPreferences[]) values.toArray(new WorkingCopyPreferences[values.size()]);
        for (int i = 0; i < valuesArray.length; i++) {
            WorkingCopyPreferences prefs = valuesArray[i];
            if (prefs.nodeExists(EMPTY_STRING))
                prefs.flush();
        }
    }
}

/***/
package org.eclipse.ui.internal.forms.widgets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.widgets.Display;

/**
* Class which can get the appropriate resource manager for a Display
* In most applications there will be only one display but see
* Bug 295981 for an example where this is not the case
*/
public class ResourceManagerManger {

    private HashMap<Display, LocalResourceManager> resourceManagers;

    public LocalResourceManager getResourceManager(Display display) {
        if (resourceManagers == null) {
            resourceManagers = new HashMap();
        }
        LocalResourceManager resources = resourceManagers.get(display);
        if (resources == null) {
            pruneResourceManagers();
            resources = new LocalResourceManager(JFaceResources.getResources(display));
            resourceManagers.put(display, resources);
        }
        return resources;
    }

    private void pruneResourceManagers() {
        Set<Display> displays = resourceManagers.keySet();
        for (Iterator<Display> iter = displays.iterator(); iter.hasNext(); ) {
            Display display = iter.next();
            if (display.isDisposed()) {
                resourceManagers.remove(display);
                iter = displays.iterator();
            }
        }
    }
}

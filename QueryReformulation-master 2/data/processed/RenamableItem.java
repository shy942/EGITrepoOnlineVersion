/***/
package org.eclipse.jface.examples.databinding.contentprovider.test;

import java.util.ArrayList;
import java.util.List;

/**
* @since 1.0
*
*/
public class RenamableItem {

    public static interface Listener {

        public void handleChanged(RenamableItem item);
    }

    private String name;

    private List<Listener> listeners = new ArrayList();

    public  RenamableItem() {
        //$NON-NLS-1$
        name = "RenamableItem";
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener toRemove) {
        listeners.remove(toRemove);
    }

    public void setName(String newName) {
        this.name = newName;
        Listener[] l = listeners.toArray(new Listener[listeners.size()]);
        for (int i = 0; i < l.length; i++) {
            l[i].handleChanged(this);
        }
    }

    public String getName() {
        return name;
    }
}

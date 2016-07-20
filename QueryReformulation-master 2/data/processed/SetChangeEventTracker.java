/***/
package org.eclipse.jface.databinding.conformance.util;

import java.util.List;
import org.eclipse.core.databinding.observable.IObservablesListener;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;

public class SetChangeEventTracker implements ISetChangeListener {

    public int count;

    public SetChangeEvent event;

    /**
* Queue that the listener will add itself too when it is notified of an
* event. Used to determine order of notifications of listeners.
*/
    public final List<IObservablesListener> listenerQueue;

    public  SetChangeEventTracker() {
        this(null);
    }

    public  SetChangeEventTracker(List<IObservablesListener> notificationQueue) {
        this.listenerQueue = notificationQueue;
    }

    @Override
    public void handleSetChange(SetChangeEvent event) {
        count++;
        this.event = event;
        if (listenerQueue != null) {
            listenerQueue.add(this);
        }
    }

    /**
* Convenience method to register a new listener.
*
* @param observable
* @return tracker
*/
    public static SetChangeEventTracker observe(IObservableSet observable) {
        SetChangeEventTracker tracker = new SetChangeEventTracker();
        observable.addSetChangeListener(tracker);
        return tracker;
    }
}

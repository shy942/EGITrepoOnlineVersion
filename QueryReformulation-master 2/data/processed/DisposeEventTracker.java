/***/
package org.eclipse.jface.databinding.conformance.util;

import java.util.List;
import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservablesListener;

/**
* Listener for tracking the firing of DisposeEvents.
*/
public class DisposeEventTracker implements IDisposeListener {

    public int count;

    public DisposeEvent event;

    /**
* Queue that the listener will add itself too when it is notified of an
* event. Used to determine order of notifications of listeners. Can be
* null.
*/
    public final List<IObservablesListener> queue;

    public  DisposeEventTracker() {
        queue = null;
    }

    public  DisposeEventTracker(List<IObservablesListener> notificationQueue) {
        this.queue = notificationQueue;
    }

    @Override
    public void handleDispose(DisposeEvent event) {
        count++;
        this.event = event;
        if (queue != null) {
            queue.add(this);
        }
    }

    /**
* Convenience method to register a new listener.
*
* @param observable
* @return tracker
*/
    public static DisposeEventTracker observe(IObservable observable) {
        DisposeEventTracker tracker = new DisposeEventTracker();
        observable.addDisposeListener(tracker);
        return tracker;
    }
}

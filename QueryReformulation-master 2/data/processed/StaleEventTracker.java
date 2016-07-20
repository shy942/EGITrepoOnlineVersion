/***/
package org.eclipse.jface.databinding.conformance.util;

import java.util.List;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservablesListener;
import org.eclipse.core.databinding.observable.IStaleListener;
import org.eclipse.core.databinding.observable.StaleEvent;

/**
* Listener for tracking the firing of StaleEvents.
*
* @since 1.1
*/
public class StaleEventTracker implements IStaleListener {

    public int count;

    public StaleEvent event;

    /**
* Queue that the listener will add itself too when it is notified of an
* event. Used to determine order of notifications of listeners. Can be
* null.
*/
    public final List<IObservablesListener> queue;

    public  StaleEventTracker() {
        this(null);
    }

    public  StaleEventTracker(List<IObservablesListener> notificationQueue) {
        this.queue = notificationQueue;
    }

    @Override
    public void handleStale(StaleEvent event) {
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
    public static StaleEventTracker observe(IObservable observable) {
        StaleEventTracker tracker = new StaleEventTracker();
        observable.addStaleListener(tracker);
        return tracker;
    }
}

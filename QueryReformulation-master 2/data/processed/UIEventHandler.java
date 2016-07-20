/***/
package org.eclipse.e4.ui.services.internal.events;

import org.eclipse.e4.ui.di.UISynchronize;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
* The helper will properly place UI-aware consumers on the main thread.
*/
public class UIEventHandler implements EventHandler {

    private final EventHandler eventHandler;

    private final UISynchronize uiSync;

    public  UIEventHandler(EventHandler eventHandler, UISynchronize uiSync) {
        this.eventHandler = eventHandler;
        this.uiSync = uiSync;
    }

    @Override
    public void handleEvent(final Event event) {
        if (uiSync == null)
            eventHandler.handleEvent(event);
        else {
            uiSync.syncExec(new Runnable() {

                @Override
                public void run() {
                    eventHandler.handleEvent(event);
                }
            });
        }
    }
}

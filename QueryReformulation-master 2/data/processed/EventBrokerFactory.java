/***/
package org.eclipse.e4.ui.services.events;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.internal.events.EventBroker;

/**
* Use this class to obtain an instance of {@link IEventBroker}.
*/
public class EventBrokerFactory extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        EventBroker broker = context.getLocal(EventBroker.class);
        if (broker == null) {
            broker = ContextInjectionFactory.make(EventBroker.class, context);
            context.set(EventBroker.class, broker);
        }
        return broker;
    }
}
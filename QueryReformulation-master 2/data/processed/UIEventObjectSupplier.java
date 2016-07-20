/***/
package org.eclipse.e4.ui.internal.di;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.internal.extensions.EventObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.di.UISynchronize;
import org.osgi.service.event.EventHandler;

public class UIEventObjectSupplier extends EventObjectSupplier {

    class UIEventHandler implements EventHandler {

        protected final IRequestor requestor;

        private final String topic;

        public  UIEventHandler(String topic, IRequestor requestor) {
            this.topic = topic;
            this.requestor = requestor;
        }

        @Override
        public void handleEvent(org.osgi.service.event.Event event) {
            if (!requestor.isValid()) {
                unsubscribe(requestor);
                return;
            }
            addCurrentEvent(topic, event);
            requestor.resolveArguments(false);
            removeCurrentEvent(topic);
            if (uiSync == null) {
                if (logger != null)
                    logger.log(Level.WARNING, "No realm found to process UI event " + event);
                return;
            } else {
                uiSync.syncExec(new Runnable() {

                    @Override
                    public void run() {
                        requestor.execute();
                    }
                });
            }
        }
    }

    @Inject
    protected UISynchronize uiSync;

    @Inject
    @Optional
    protected Logger logger;

    @Override
    protected EventHandler makeHandler(String topic, IRequestor requestor) {
        return new UIEventHandler(topic, requestor);
    }

    @Override
    protected String getTopic(IObjectDescriptor descriptor) {
        if (descriptor == null)
            return null;
        UIEventTopic qualifier = descriptor.getQualifier(UIEventTopic.class);
        return qualifier.value();
    }
}

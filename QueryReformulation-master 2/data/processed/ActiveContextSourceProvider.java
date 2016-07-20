/***/
package org.eclipse.ui.internal.contexts;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import org.eclipse.core.commands.contexts.ContextManagerEvent;
import org.eclipse.core.commands.contexts.IContextManagerListener;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.services.IServiceLocator;

/**
* <p>
* This listens to changes to the list of active contexts, and propagates them
* through the <code>ISourceProvider</code> framework (a common language in
* which events are communicated to services).
* </p>
*
* @since 3.2
*/
public final class ActiveContextSourceProvider extends AbstractSourceProvider implements IContextManagerListener {

    /**
* The names of the sources supported by this source provider.
*/
    private static final String[] PROVIDED_SOURCE_NAMES = new String[] { ISources.ACTIVE_CONTEXT_NAME };

    /**
* The context service with which this source provider should communicate.
* This value is never <code>null</code>.
*/
    private IContextService contextService;

    @Override
    public final void contextManagerChanged(final ContextManagerEvent event) {
        if (event.isActiveContextsChanged()) {
            final Map currentState = getCurrentState();
            if (DEBUG) {
                logDebuggingInfo(//$NON-NLS-1$
                "Contexts changed to " + currentState.get(ISources.ACTIVE_CONTEXT_NAME));
            }
            fireSourceChanged(ISources.ACTIVE_CONTEXT, currentState);
        }
    }

    @Override
    public final void dispose() {
        contextService.removeContextManagerListener(this);
    }

    @Override
    public final Map getCurrentState() {
        final Map currentState = new TreeMap();
        final Collection activeContextIds = contextService.getActiveContextIds();
        currentState.put(ISources.ACTIVE_CONTEXT_NAME, activeContextIds);
        return currentState;
    }

    @Override
    public final String[] getProvidedSourceNames() {
        return PROVIDED_SOURCE_NAMES;
    }

    @Override
    public void initialize(IServiceLocator locator) {
        contextService = locator.getService(IContextService.class);
        contextService.addContextManagerListener(this);
    }
}

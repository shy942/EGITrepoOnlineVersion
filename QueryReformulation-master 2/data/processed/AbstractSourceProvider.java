/***/
package org.eclipse.ui;

import java.util.Map;
import org.eclipse.core.commands.util.Tracing;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.ui.internal.misc.Policy;
import org.eclipse.ui.services.IServiceLocator;

/**
* <p>
* An implementation of <code>ISourceProvider</code> that provides listener
* support. Subclasses need only call <code>fireSourceChanged</code> whenever
* appropriate.
* </p>
*
* @since 3.1
*/
public abstract class AbstractSourceProvider implements ISourceProvider {

    /**
* Whether source providers should print out debugging information to the
* console when events arrive.
*
* @since 3.2
*/
    protected static boolean DEBUG = Policy.DEBUG_SOURCES;

    /**
* The listeners to this source provider. This value is never
* <code>null</code>.
*/
    private final ListenerList listeners = new ListenerList(ListenerList.IDENTITY);

    @Override
    public final void addSourceProviderListener(final ISourceProviderListener listener) {
        if (listener == null) {
            //$NON-NLS-1$
            throw new NullPointerException("The listener cannot be null");
        }
        listeners.add(listener);
    }

    /**
* Notifies all listeners that a single source has changed.
*
* @param sourcePriority
*            The source priority that has changed.
* @param sourceName
*            The name of the source that has changed; must not be
*            <code>null</code>.
* @param sourceValue
*            The new value for the source; may be <code>null</code>.
*/
    protected final void fireSourceChanged(final int sourcePriority, final String sourceName, final Object sourceValue) {
        for (Object listener : listeners.getListeners()) {
            ((ISourceProviderListener) listener).sourceChanged(sourcePriority, sourceName, sourceValue);
        }
    }

    /**
* Notifies all listeners that multiple sources have changed.
*
* @param sourcePriority
*            The source priority that has changed.
* @param sourceValuesByName
*            The map of source names (<code>String</code>) to source
*            values (<code>Object</code>) that have changed; must not
*            be <code>null</code>. The names must not be
*            <code>null</code>, but the values may be <code>null</code>.
*/
    protected final void fireSourceChanged(final int sourcePriority, final Map sourceValuesByName) {
        for (Object listener : listeners.getListeners()) {
            ((ISourceProviderListener) listener).sourceChanged(sourcePriority, sourceValuesByName);
        }
    }

    /**
* Logs a debugging message in an appropriate manner. If the message is
* <code>null</code> or the <code>DEBUG</code> is <code>false</code>,
* then this method does nothing.
*
* @param message
*            The debugging message to log; if <code>null</code>, then
*            nothing is logged.
* @since 3.2
*/
    protected final void logDebuggingInfo(final String message) {
        if (DEBUG && (message != null)) {
            //$NON-NLS-1$
            Tracing.printTrace("SOURCES", message);
        }
    }

    @Override
    public final void removeSourceProviderListener(final ISourceProviderListener listener) {
        if (listener == null) {
            //$NON-NLS-1$
            throw new NullPointerException("The listener cannot be null");
        }
        listeners.remove(listener);
    }

    /**
* This method is called when the source provider is instantiated by
* <code>org.eclipse.ui.services</code>. Clients may override this method
* to perform initialization.
*
* @param locator
*            The global service locator. It can be used to retrieve
*            services like the IContextService
* @since 3.4
*/
    public void initialize(final IServiceLocator locator) {
    }
}

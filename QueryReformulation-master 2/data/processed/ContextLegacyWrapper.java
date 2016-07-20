/***/
package org.eclipse.ui.internal.contexts;

import org.eclipse.core.commands.contexts.Context;
import org.eclipse.core.commands.contexts.ContextManager;
import org.eclipse.ui.contexts.IContext;
import org.eclipse.ui.contexts.IContextListener;
import org.eclipse.ui.contexts.NotDefinedException;
import org.eclipse.ui.internal.util.Util;

/**
* This implements the old <code>IContext</code> interface based on the new
* context implementation in <code>org.eclipse.ui.contexts</code>. This is a
* wrapper.
*
* @since 3.1
*/
public class ContextLegacyWrapper implements IContext {

    /**
* The context manager that maintains the set of active contexts; must not
* be <code>null</code>.
*/
    private final ContextManager contextManager;

    /**
* The wrapped instance of context. This value will never be
* <code>null</code>.
*/
    private final Context wrappedContext;

    /**
* Constructs a new instance of <code>ContextWrapper</code>.
*
* @param context
*            The context to wrapper; must not be <code>null</code>.
* @param contextManager
*            The context manager that maintains the set of active contexts;
*            must not be <code>null</code>.
*/
    public  ContextLegacyWrapper(final Context context, final ContextManager contextManager) {
        if (context == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "A wrapper cannot be created on a null context");
        }
        if (contextManager == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "A wrapper cannot be created with a null manager");
        }
        wrappedContext = context;
        this.contextManager = contextManager;
    }

    @Override
    public void addContextListener(IContextListener contextListener) {
        final LegacyContextListenerWrapper wrapper = new LegacyContextListenerWrapper(contextListener, contextManager, this);
        wrappedContext.addContextListener(wrapper);
        /*
* We need to add the listener to the context manager as well, as only
* the manager advertises changes to the enabled state.
*/
        contextManager.addContextManagerListener(wrapper);
    }

    @Override
    public int compareTo(Object o) {
        return Util.compare(wrappedContext, ((ContextLegacyWrapper) o).wrappedContext);
    }

    @Override
    public String getId() {
        return wrappedContext.getId();
    }

    @Override
    public String getName() throws NotDefinedException {
        try {
            return wrappedContext.getName();
        } catch (final org.eclipse.core.commands.common.NotDefinedException e) {
            throw new NotDefinedException(e);
        }
    }

    @Override
    public String getParentId() throws NotDefinedException {
        try {
            return wrappedContext.getParentId();
        } catch (final org.eclipse.core.commands.common.NotDefinedException e) {
            throw new NotDefinedException(e);
        }
    }

    @Override
    public boolean isDefined() {
        return wrappedContext.isDefined();
    }

    @Override
    public boolean isEnabled() {
        return contextManager.getActiveContextIds().contains(wrappedContext.getId());
    }

    @Override
    public void removeContextListener(IContextListener contextListener) {
        final LegacyContextListenerWrapper wrapper = new LegacyContextListenerWrapper(contextListener, contextManager, this);
        wrappedContext.removeContextListener(wrapper);
        contextManager.removeContextManagerListener(wrapper);
    }
}

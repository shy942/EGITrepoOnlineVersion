/***/
package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.commands.HandlerEvent;
import org.eclipse.ui.commands.IHandlerListener;
import org.eclipse.ui.internal.commands.ILegacyAttributeNames;

/**
* A wrapper so that the new handler listener can work with legacy handlers.
* This class is only intended for backward compatibility with Eclipse 3.0.
*
* @since 3.1
*/
public final class LegacyHandlerListenerWrapper implements IHandlerListener {

    /**
* The handler on which this listener is listening; never <code>null</code>.
*/
    private final IHandler handler;

    /**
* The wrapped listener; never <code>null</code>.
*/
    private final org.eclipse.core.commands.IHandlerListener listener;

    /**
* Constructs a new instance of <code>LegacyHandlerListenerWrapper</code>.
*
* @param listener
*            The listener to wrap; must not be <code>null</code>.
*/
    public  LegacyHandlerListenerWrapper(final IHandler handler, final org.eclipse.core.commands.IHandlerListener listener) {
        if (handler == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "A listener wrapper cannot be created on a null handler");
        }
        if (listener == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "A listener wrapper cannot be created on a null listener");
        }
        this.handler = handler;
        this.listener = listener;
    }

    @Override
    public void handlerChanged(HandlerEvent event) {
        final boolean enabledChanged = ((Boolean) event.getPreviousAttributeValuesByName().get(ILegacyAttributeNames.ENABLED)).booleanValue() != handler.isEnabled();
        final boolean handledChanged = ((Boolean) event.getPreviousAttributeValuesByName().get(ILegacyAttributeNames.HANDLED)).booleanValue() != handler.isHandled();
        listener.handlerChanged(new org.eclipse.core.commands.HandlerEvent(handler, enabledChanged, handledChanged));
    }
}

/***/
package org.eclipse.core.commands;

/**
* Extend the IHandler interface to provide some context for isEnabled()
* requests. Clients should use {@link AbstractHandler} unless they need to
* provide their own listener mechanism.
*
* @since 3.4
* @see AbstractHandler
*/
public interface IHandler2 extends IHandler {

    /**
* Called by the framework to allow the handler to update its enabled state.
*
* @param evaluationContext
*            the state to evaluate against. May be <code>null</code>
*            which indicates that the handler can query whatever model that
*            is necessary. This context must not be cached.
*/
    public void setEnabled(Object evaluationContext);
}

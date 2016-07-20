/***/
package org.eclipse.e4.ui.workbench;

/**
* This handler allows clients to be notified when an exception occurs
*
* @noimplement This interface is not intended to be implemented by clients.
* @since 1.0
*/
public interface IExceptionHandler {

    /**
* Call-back to handle the given {@link Throwable}
*
* @param e
*            the {@link Throwable}
*/
    public void handleException(Throwable e);
}

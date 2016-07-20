/***/
package org.eclipse.jface.util;

import org.eclipse.core.runtime.IStatus;

/**
* A mechanism to handle statuses throughout JFace.
* <p>
* Clients may provide their own implementation to change how statuses are
* handled from within JFace.
* </p>
*
* @see org.eclipse.jface.util.Policy#getStatusHandler()
* @see org.eclipse.jface.util.Policy#setStatusHandler(StatusHandler)
*
* @since 3.4
*/
public abstract class StatusHandler {

    /**
* Show the given status.
*
* @param status
*            status to handle
* @param title
*            title for the status
*/
    public abstract void show(IStatus status, String title);
}

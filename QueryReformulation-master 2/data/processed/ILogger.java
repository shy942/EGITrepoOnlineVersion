/***/
package org.eclipse.jface.util;

import org.eclipse.core.runtime.IStatus;

/**
* A mechanism to log errors throughout JFace.
* <p>
* Clients may provide their own implementation to change
* how errors are logged from within JFace.
* </p>
*
* @see org.eclipse.jface.util.Policy#getLog()
* @see org.eclipse.jface.util.Policy#setLog(ILogger)
* @since 3.1
*/
@FunctionalInterface
public interface ILogger {

    /**
* Logs the given status.
*
* @param status the status to log
*/
    public void log(IStatus status);
}

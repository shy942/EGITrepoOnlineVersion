/***/
package org.eclipse.jface.util;

import org.eclipse.core.runtime.ISafeRunnable;

/**
* Runs a safe runnables.
* <p>
* Clients may provide their own implementation to change
* how safe runnables are run from within JFace.
* </p>
*
* @see SafeRunnable#getRunner()
* @see SafeRunnable#setRunner(ISafeRunnableRunner)
* @see SafeRunnable#run(ISafeRunnable)
* @since 3.1
*/
public interface ISafeRunnableRunner {

    /**
* Runs the runnable.  All <code>ISafeRunnableRunners</code> must catch any exception
* thrown by the <code>ISafeRunnable</code> and pass the exception to
* <code>ISafeRunnable.handleException()</code>.
* @param code the code executed as a save runnable
*
* @see SafeRunnable#run(ISafeRunnable)
*/
    public abstract void run(ISafeRunnable code);
}

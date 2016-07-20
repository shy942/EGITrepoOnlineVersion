/***/
package org.eclipse.jface.util;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.JFaceResources;

/**
* Implements a default implementation of ISafeRunnable. The default
* implementation of <code>handleException</code> opens a dialog to show any
* errors as they accumulate.
* <p>
* This may be executed on any thread.
*/
public abstract class SafeRunnable implements ISafeRunnable {

    private static boolean ignoreErrors = false;

    private static ISafeRunnableRunner runner;

    private String message;

    /**
* Creates a new instance of SafeRunnable with a default error message.
*/
    public  SafeRunnable() {
    // do nothing
    }

    /**
* Creates a new instance of SafeRunnable with the given error message.
*
* @param message
*            the error message to use
*/
    public  SafeRunnable(String message) {
        this.message = message;
    }

    @Override
    public void handleException(Throwable e) {
        // automated testing
        if (ignoreErrors)
            return;
        if (message == null)
            //$NON-NLS-1$
            message = JFaceResources.getString("SafeRunnable.errorMessage");
        Policy.getStatusHandler().show(new Status(IStatus.ERROR, Policy.JFACE, message, e), //$NON-NLS-1$
        JFaceResources.getString("SafeRunnable.errorMessage"));
    }

    /**
* Flag to avoid interactive error dialogs during automated testing.
*
* @param flag
* @return true if errors should be ignored
* @deprecated use getIgnoreErrors()
*/
    @Deprecated
    public static boolean getIgnoreErrors(boolean flag) {
        return ignoreErrors;
    }

    /**
* Flag to avoid interactive error dialogs during automated testing.
*
* @return true if errors should be ignored
*
* @since 3.0
*/
    public static boolean getIgnoreErrors() {
        return ignoreErrors;
    }

    /**
* Flag to avoid interactive error dialogs during automated testing.
*
* @param flag
*            set to true if errors should be ignored
*/
    public static void setIgnoreErrors(boolean flag) {
        ignoreErrors = flag;
    }

    /**
* Returns the safe runnable runner.
*
* @return the safe runnable runner
*
* @since 3.1
*/
    public static ISafeRunnableRunner getRunner() {
        if (runner == null) {
            runner = createDefaultRunner();
        }
        return runner;
    }

    /**
* Creates the default safe runnable runner.
*
* @return the default safe runnable runner
* @since 3.1
*/
    private static ISafeRunnableRunner createDefaultRunner() {
        return new ISafeRunnableRunner() {

            @Override
            public void run(ISafeRunnable code) {
                try {
                    code.run();
                } catch (Exception e) {
                    handleException(code, e);
                } catch (LinkageError e) {
                    handleException(code, e);
                }
            }

            private void handleException(ISafeRunnable code, Throwable e) {
                if (!(e instanceof OperationCanceledException)) {
                    try {
                        Policy.getLog().log(new Status(IStatus.ERROR, Policy.JFACE, IStatus.ERROR, "Exception occurred", //$NON-NLS-1$
                        e));
                    } catch (Exception ex) {
                        e.printStackTrace();
                    }
                }
                code.handleException(e);
            }
        };
    }

    /**
* Sets the safe runnable runner.
*
* @param runner
*            the runner to set, or <code>null</code> to reset to the
*            default runner
* @since 3.1
*/
    public static void setRunner(ISafeRunnableRunner runner) {
        SafeRunnable.runner = runner;
    }

    /**
* Runs the given safe runnable using the safe runnable runner. This is a
* convenience method, equivalent to:
* <code>SafeRunnable.getRunner().run(runnable)</code>.
*
* @param runnable
*            the runnable to run
* @since 3.1
*/
    public static void run(ISafeRunnable runnable) {
        getRunner().run(runnable);
    }
}
/***/
package org.eclipse.core.databinding;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
* An unchecked exception indicating a binding problem.
*
* @since 1.0
*/
public class BindingException extends RuntimeException {

    /*
* Needed because all Throwables are Serializable.
*/
    private static final long serialVersionUID = -4092828452936724217L;

    private Throwable cause;

    /**
* Creates a new BindingException with the given message.
*
* @param message
*/
    public  BindingException(String message) {
        super(message);
    }

    /**
* Creates a new BindingException with the given message and cause.
*
* @param message
* @param cause
*/
    public  BindingException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    @Override
    public void printStackTrace(PrintStream err) {
        super.printStackTrace(err);
        if (cause != null) {
            //$NON-NLS-1$
            err.println("caused by:");
            cause.printStackTrace(err);
        }
    }

    @Override
    public void printStackTrace(PrintWriter err) {
        super.printStackTrace(err);
        if (cause != null) {
            //$NON-NLS-1$
            err.println("caused by:");
            cause.printStackTrace(err);
        }
    }
}

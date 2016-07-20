/***/
package org.eclipse.ui.tests.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
* An intentionally thrown exception for use in testing error handling code.
*
* @since 3.1
*/
public class ForcedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
* Creates a <code>ForcedException</code> with the given message.
*
* @param message the message
*/
    public  ForcedException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        s.println("!FORCED BY TEST: this entry is intentional: " + getMessage());
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        s.println("!FORCED BY TEST: this entry is intentional:" + getMessage());
    }
}

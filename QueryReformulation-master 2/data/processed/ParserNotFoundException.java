/***/
package org.eclipse.e4.ui.css.core.sac;

/**
* Exception used when SAC parser is not retrieved.
*/
public class ParserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4339161134287845644L;

    public  ParserNotFoundException(Throwable throwable) {
        super(throwable);
    }
}

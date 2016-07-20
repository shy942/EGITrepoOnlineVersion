/***/
package org.eclipse.e4.ui.css.core.impl.engine;

import org.eclipse.e4.ui.css.core.engine.CSSErrorHandler;

/**
* Basic implementation for CSS Engine error handlers which print stack trace of
* the exception throwed.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public class CSSErrorHandlerImpl implements CSSErrorHandler {

    public static final CSSErrorHandler INSTANCE = new CSSErrorHandlerImpl();

    @Override
    public void error(Exception e) {
        e.printStackTrace();
    }
}

/***/
package org.eclipse.e4.ui.css.core.engine;

/**
* Basic interface for CSS Engine error handlers.
*/
public interface CSSErrorHandler {

    /**
* Callback method called when CSS engien catch errors.
*
* @param e
*/
    public void error(Exception e);
}

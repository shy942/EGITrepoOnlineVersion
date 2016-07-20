/***/
package org.eclipse.jface.viewers;

/**
* A listener which is notified of open events on viewers.
*/
public interface IOpenListener {

    /**
* Notifies of an open event.
*
* @param event event object describing the open event
*/
    public void open(OpenEvent event);
}

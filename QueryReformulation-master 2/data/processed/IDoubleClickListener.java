/***/
package org.eclipse.jface.viewers;

/**
* A listener which is notified of double-click events on viewers.
*/
public interface IDoubleClickListener {

    /**
* Notifies of a double click.
*
* @param event event object describing the double-click
*/
    public void doubleClick(DoubleClickEvent event);
}

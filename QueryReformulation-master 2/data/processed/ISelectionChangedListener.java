/***/
package org.eclipse.jface.viewers;

/**
* A listener which is notified when a viewer's selection changes.
*
* @see ISelection
* @see ISelectionProvider
* @see SelectionChangedEvent
*/
@FunctionalInterface
public interface ISelectionChangedListener {

    /**
* Notifies that the selection has changed.
*
* @param event event object describing the change
*/
    public void selectionChanged(SelectionChangedEvent event);
}

/***/
package org.eclipse.jface.viewers;

/**
* Interface for a selection.
*
* @see ISelectionProvider
* @see ISelectionChangedListener
* @see SelectionChangedEvent
*/
public interface ISelection {

    /**
* Returns whether this selection is empty.
*
* @return <code>true</code> if this selection is empty,
*   and <code>false</code> otherwise
*/
    public boolean isEmpty();
}

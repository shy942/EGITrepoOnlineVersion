/***/
package org.eclipse.jface.dialogs;

/**
* A listener which is notified when the current page of the multi-page dialog
* is changed.
*
* @see IPageChangeProvider
* @see PageChangedEvent
*
* @since 3.1
*/
public interface IPageChangedListener {

    /**
* Notifies that the selected page has changed.
*
* @param event
*            event object describing the change
*/
    public void pageChanged(PageChangedEvent event);
}

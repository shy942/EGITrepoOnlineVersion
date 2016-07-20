/***/
package org.eclipse.jface.dialogs;

/**
* A listener which is notified when the current page of a multi-page dialog is
* changing. Use this listener to perform long-running work that should only be
* executed once, when the page is in the process of changing, rather then
* during validation of page controls.
*
* @see PageChangingEvent
* @since 3.3
*/
public interface IPageChangingListener {

    /**
* Handle the an <code>IDialogPage</code> changing.
*
* The <code>doit</code> field of the <code>PageChangingEvent</code>
* must be set to false to prevent the page from changing.
*
* @param event
*            event object describing the change
*/
    public void handlePageChanging(PageChangingEvent event);
}

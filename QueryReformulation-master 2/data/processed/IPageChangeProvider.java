/***/
package org.eclipse.jface.dialogs;

/**
* Minimal interface to a page change provider. Used for dialogs which can
* switch between multiple pages.
*
* @since 3.1
*/
public interface IPageChangeProvider {

    /**
* Returns the currently selected page in the dialog.
*
* @return the selected page in the dialog or <code>null</code> if none is
*         selected. The type may be domain specific. In
*         the JFace provided dialogs this will be an instance of
*         <code>IDialogPage</code>.
*/
    Object getSelectedPage();

    /**
* Adds a listener for page changes in this page change provider. Has no
* effect if an identical listener is already registered.
*
* @param listener
*            a page changed listener
*/
    void addPageChangedListener(IPageChangedListener listener);

    /**
* Removes the given page change listener from this page change provider.
* Has no effect if an identical listener is not registered.
*
* @param listener
*            a page changed listener
*/
    void removePageChangedListener(IPageChangedListener listener);
}

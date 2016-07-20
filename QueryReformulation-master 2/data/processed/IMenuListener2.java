/***/
package org.eclipse.jface.action;

/**
* A menu listener that gets informed when a menu is about to hide.
*
* @see MenuManager#addMenuListener
* @since 3.2
*/
public interface IMenuListener2 extends IMenuListener {

    /**
* Notifies this listener that the menu is about to be hidden by
* the given menu manager.
*
* @param manager the menu manager
*/
    public void menuAboutToHide(IMenuManager manager);
}

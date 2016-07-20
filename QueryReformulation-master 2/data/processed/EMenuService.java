/***/
package org.eclipse.e4.ui.services;

/**
* Provide for management of different menus.
*
* @noimplement This interface is not intended to be implemented by clients.
* @since 1.1
*/
public interface EMenuService {

    /**
* Create a menu for this control and hook it up with the MPopupMenu.
*
* @param parent
*            The parent for the context menu. A Control in SWT.
* @param menuId
*            the ID of the menu to use
* @return <code>true</code> if registration succeeded else <code>false</code>
*/
    boolean registerContextMenu(Object parent, String menuId);
}

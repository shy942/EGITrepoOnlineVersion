/***/
package org.eclipse.ui;

import org.eclipse.swt.widgets.Menu;

/**
* Extension of IWorkbenchWindowPulldownDelegate that allows the delegate dropdown
* menu to be a child of a Menu item.  Necessary for CoolBar support.  If a coolbar
* group of items is not fully displayed, a chevron and a drop down menu will be
* used to show the group's tool items.  Therefore, a getMenu(Menu) method is necessary,
* since the delegate drop down menu will be a child of the chevron menu item (not
* the tool control).
*/
public interface IWorkbenchWindowPulldownDelegate2 extends IWorkbenchWindowPulldownDelegate {

    /**
* Returns the menu for this pull down action.  This method will only be
* called if the user opens the pull down menu for the action.  Note that it
* is the responsibility of the implementor to properly dispose of any SWT
* menus created by this method.
*
* @return the menu
*/
    public Menu getMenu(Menu parent);
}

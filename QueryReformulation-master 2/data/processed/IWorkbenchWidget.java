/***/
package org.eclipse.ui.menus;

import org.eclipse.jface.menus.IWidget;
import org.eclipse.ui.IWorkbenchWindow;

/**
* Interface used for IWidget's contributed to the
* Workbench. Allows the contributed widget to be
* informed as to which WorkbenchWindow it's being
* hosted in.
*
* @see org.eclipse.jface.menus.IWidget
*
* @since 3.2
*/
public interface IWorkbenchWidget extends IWidget {

    /**
* Initializes this widget contribution by supplying the
* <code>IWorkbenchWindow</code> that it's being hosted in.
* <p>
* This method is called after the no argument constructor and
* before other methods are called.
* </p>
*
* @param workbenchWindow the current workbench
*/
    void init(IWorkbenchWindow workbenchWindow);
}

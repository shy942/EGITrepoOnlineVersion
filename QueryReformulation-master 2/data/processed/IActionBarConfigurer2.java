/***/
package org.eclipse.ui.internal.provisional.application;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.internal.provisional.action.IToolBarContributionItem;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
* Extends <code>IActionBarConfigurer</code> with API to allow the advisor to
* be decoupled from the implementation types for tool bar managers and tool bar
* contribution items.
*
* @since 3.2
*/
public interface IActionBarConfigurer2 extends IActionBarConfigurer {

    /**
* Creates a tool bar manager for the workbench window's tool bar. The
* action bar advisor should use this factory method rather than creating a
* <code>ToolBarManager</code> directly.
*
* @return the tool bar manager
*/
    public IToolBarManager createToolBarManager();

    /**
* Creates a toolbar contribution item for the window's tool bar. The action
* bar advisor should use this factory method rather than creating a
* <code>ToolBarContributionItem</code> directly.
*
* @param toolBarManager
*            a tool bar manager for the workbench window's tool bar
* @param id
*            the id of the contribution
* @return the tool bar contribution item
*/
    public IToolBarContributionItem createToolBarContributionItem(IToolBarManager toolBarManager, String id);
}

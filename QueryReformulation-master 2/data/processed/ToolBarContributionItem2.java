/***/
package org.eclipse.jface.internal.provisional.action;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;

/**
* Extends <code>ToolBarContributionItem</code> to implement <code>IToolBarContributionItem</code>.
*
* <p>
* <strong>EXPERIMENTAL</strong>. This class or interface has been added as
* part of a work in progress. There is a guarantee neither that this API will
* work nor that it will remain the same. Please do not use this API without
* consulting with the Platform/UI team.
* </p>
*
* @since 3.2
*/
public class ToolBarContributionItem2 extends ToolBarContributionItem {

    /**
*
*/
    public  ToolBarContributionItem2() {
        super();
    }

    /**
* @param toolBarManager
*/
    public  ToolBarContributionItem2(IToolBarManager toolBarManager) {
        super(toolBarManager);
    }

    /**
* @param toolBarManager
* @param id
*/
    public  ToolBarContributionItem2(IToolBarManager toolBarManager, String id) {
        super(toolBarManager, id);
    }
}

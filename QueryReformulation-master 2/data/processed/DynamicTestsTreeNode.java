/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.views;

import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsElement;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
* A nodes in the tree of the dynamic tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class DynamicTestsTreeNode extends TreeNode implements ITabbedPropertySheetPageContributor {

    public  DynamicTestsTreeNode(Object object) {
        super(object);
    }

    @Override
    public String getContributorId() {
        return getDynamicTestsElement().getContributorId();
    }

    public DynamicTestsElement getDynamicTestsElement() {
        return (DynamicTestsElement) getValue();
    }
}

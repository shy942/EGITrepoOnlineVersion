/***/
package org.eclipse.ui.tests.views.properties.tabbed.views;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;

/**
* A filter for Information messages that have the work "Two" in them.
*
* @author Anthony Hunter
*/
public class InformationTwoFilter implements IFilter {

    @Override
    public boolean select(Object object) {
        if (object instanceof TreeNode) {
            Element element = (Element) ((TreeNode) object).getValue();
            if (element instanceof Information) {
                Information information = (Information) element;
                if (information.getName().indexOf("Two") > -1) {
                    //$NON-NLS-1$
                    return true;
                }
            }
        }
        return false;
    }
}

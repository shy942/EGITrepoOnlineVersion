/***/
package org.eclipse.ui.internal.dialogs;

import java.util.Collection;
import java.util.HashSet;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
* The PreferenceNodeFilter is a filter that only matches
* a set of ids.
*/
public class PreferenceNodeFilter extends ViewerFilter {

    Collection ids = new HashSet();

    /**
* Create a new instance of the receiver on a
* list of filteredIds.
* @param filteredIds The collection of ids that
* will be shown.
*/
    public  PreferenceNodeFilter(String[] filteredIds) {
        super();
        for (int i = 0; i < filteredIds.length; i++) {
            ids.add(filteredIds[i]);
        }
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        return checkNodeAndChildren((IPreferenceNode) element);
    }

    /**
* Check to see if the node or any of its children
* have an id in the ids.
* @param node WorkbenchPreferenceNode
* @return boolean <code>true</code> if node or oe of its children
* has an id in the ids.
*/
    private boolean checkNodeAndChildren(IPreferenceNode node) {
        if (ids.contains(node.getId())) {
            return true;
        }
        IPreferenceNode[] subNodes = node.getSubNodes();
        for (int i = 0; i < subNodes.length; i++) {
            if (checkNodeAndChildren(subNodes[i])) {
                return true;
            }
        }
        return false;
    }
}
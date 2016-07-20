/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;

/**
* The MarkersTreeViewer is a viewer that optimizes the expandToLevel method.
*
* @since 3.4
*
*/
public class MarkersTreeViewer extends TreeViewer {

    /**
* Create a new instance of the receiver.
*
* @param parent
* @param style
*/
    public  MarkersTreeViewer(Composite parent, int style) {
        super(parent, style);
    }

    /**
* Create a new instance of the receiver.
*
* @param parent
*/
    public  MarkersTreeViewer(Composite parent) {
        super(parent);
    }

    /**
* Create a new instance of the receiver.
*
* @param tree
*/
    public  MarkersTreeViewer(Tree tree) {
        super(tree);
    }

    /**
* Remove all of the entries and unmap all of the elements.
*/
    public void removeAndClearAll() {
        removeAll(getControl());
        unmapAllElements();
    }

    @Override
    protected void doUpdateItem(Item item, Object element) {
        super.doUpdateItem(item, element);
        /*
* For performance reasons clear cache of the item used in updating UI.
*/
        MarkerSupportItem cellItem = (MarkerSupportItem) element;
        if (cellItem.isConcrete())
            cellItem.clearCache();
    }
}

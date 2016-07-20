/***/
package org.eclipse.ui.internal.dialogs.cpd;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.internal.dialogs.cpd.TreeManager.CheckListener;
import org.eclipse.ui.internal.dialogs.cpd.TreeManager.TreeItem;

/**
* On a model change, update a filtered listener. While the check listener
* provided by the model will take care of the elements which change, since
* we simulate our own check state of parents, the parents may need to be
* updated.
*
* @since 3.5
*/
final class FilteredModelCheckListener implements CheckListener {

    private final ActionSetFilter filter;

    private final StructuredViewer viewer;

     FilteredModelCheckListener(ActionSetFilter filter, StructuredViewer viewer) {
        this.filter = filter;
        this.viewer = viewer;
    }

    @Override
    public void checkChanged(TreeItem changedItem) {
        TreeItem item = changedItem;
        boolean update = false;
        // Force an update on all parents.
        while (item != null) {
            update = update || filter.select(null, null, item);
            if (update) {
                viewer.update(item, null);
            }
            item = item.getParent();
        }
    }
}

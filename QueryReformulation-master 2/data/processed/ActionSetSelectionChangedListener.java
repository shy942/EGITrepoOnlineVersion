/***/
package org.eclipse.ui.internal.dialogs.cpd;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.internal.dialogs.cpd.CustomizePerspectiveDialog.ActionSet;

/**
* A Listener for a list of command groups, that updates the viewer and filter
* who are dependent on the action set selection.
*
* @since 3.5
*/
final class ActionSetSelectionChangedListener implements ISelectionChangedListener {

    private final TreeViewer filterViewer;

    private final ActionSetFilter filter;

    public  ActionSetSelectionChangedListener(TreeViewer viewer, ActionSetFilter menuStructureFilterByActionSet) {
        this.filterViewer = viewer;
        this.filter = menuStructureFilterByActionSet;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        Object element = ((IStructuredSelection) event.getSelection()).getFirstElement();
        filter.setActionSet((ActionSet) element);
        filterViewer.refresh();
        filterViewer.expandAll();
    }
}

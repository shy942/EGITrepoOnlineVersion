/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.jface.viewers.CheckboxTreeViewer;

/**
* @since 3.3
*
*/
public class CheckboxTreeViewerUpdater extends TreeViewerUpdater {

    private final CheckboxTreeViewer checkboxViewer;

     CheckboxTreeViewerUpdater(CheckboxTreeViewer viewer) {
        super(viewer);
        checkboxViewer = viewer;
    }

    @Override
    public void move(Object parent, Object element, int oldPosition, int newPosition) {
        if (isElementOrderPreserved()) {
            boolean wasChecked = checkboxViewer.getChecked(element);
            boolean wasGrayed = checkboxViewer.getGrayed(element);
            super.move(parent, element, oldPosition, newPosition);
            checkboxViewer.setChecked(element, wasChecked);
            checkboxViewer.setGrayed(element, wasGrayed);
        }
    }
}

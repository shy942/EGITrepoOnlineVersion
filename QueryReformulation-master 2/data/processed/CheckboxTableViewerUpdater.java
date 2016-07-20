/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.jface.viewers.CheckboxTableViewer;

/**
* @since 3.3
*
*/
public class CheckboxTableViewerUpdater extends TableViewerUpdater {

    private final CheckboxTableViewer checkboxViewer;

     CheckboxTableViewerUpdater(CheckboxTableViewer viewer) {
        super(viewer);
        checkboxViewer = viewer;
    }

    @Override
    public void move(Object element, int oldPosition, int newPosition) {
        if (isElementOrderPreserved()) {
            boolean wasChecked = checkboxViewer.getChecked(element);
            boolean wasGrayed = checkboxViewer.getGrayed(element);
            super.move(element, oldPosition, newPosition);
            checkboxViewer.setChecked(element, wasChecked);
            checkboxViewer.setGrayed(element, wasGrayed);
        }
    }
}

/***/
package org.eclipse.jface.snippets.viewers;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.FocusCellHighlighter;
import org.eclipse.jface.viewers.ViewerCell;

/**
* @since 3.3
*
*/
public class CursorCellHighlighter extends FocusCellHighlighter {

    private ColumnViewer viewer;

    private AbstractCellCursor cursor;

    /**
* @param viewer
* @param cursor
*/
    public  CursorCellHighlighter(ColumnViewer viewer, AbstractCellCursor cursor) {
        super(viewer);
        this.viewer = viewer;
        this.cursor = cursor;
    }

    @Override
    protected void focusCellChanged(ViewerCell cell) {
        super.focusCellChanged(cell);
        if (cell != null && !viewer.isCellEditorActive()) {
            //$NON-NLS-1$
            System.err.println("SHOW EDITOR");
            //TODO THE TIME
            cursor.setSelection(cell, 0);
            cursor.setVisible(true);
        }
    }

    @Override
    protected void init() {
        hookListener();
    }

    private void hookListener() {
        ColumnViewerEditorActivationListener listener = new ColumnViewerEditorActivationListener() {

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
            }

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                cursor.setVisible(true);
                //TODO THE TIME
                cursor.setSelection(getFocusCell(), 0);
            }

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
                cursor.setVisible(false);
            }

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
            }
        };
        viewer.getColumnViewerEditor().addEditorActivationListener(listener);
    }
}

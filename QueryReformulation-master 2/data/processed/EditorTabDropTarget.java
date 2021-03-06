/***/
package org.eclipse.ui.tests.dnd;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

/**
* @since 3.0
*/
public class EditorTabDropTarget extends WorkbenchWindowDropTarget {

    int editorIdx;

    public  EditorTabDropTarget(IWorkbenchWindowProvider provider, int editorIdx) {
        super(provider);
        this.editorIdx = editorIdx;
    }

    IEditorPart getPart() {
        return getPage().getEditors()[editorIdx];
    }

    @Override
    public String toString() {
        return "editor " + editorIdx + " tab area";
    }

    @Override
    public Shell getShell() {
        return getPart().getSite().getShell();
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = DragOperations.getDisplayBounds(DragOperations.getPane(getPart()));
        return new Point(bounds.x + 8, bounds.y + 8);
    }
}

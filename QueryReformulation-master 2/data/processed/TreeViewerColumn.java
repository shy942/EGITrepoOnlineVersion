/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
* ViewerColumn implementation for TreeViewer to enable column-specific label
* providers and editing support.
*
* @since 3.3
*
*/
public final class TreeViewerColumn extends ViewerColumn {

    private TreeColumn column;

    /**
* Creates a new viewer column for the given {@link TreeViewer} on a new
* {@link TreeColumn} with the given style bits. The column is inserted at
* the given index into the list of columns.
*
* @param viewer
*            the tree viewer to which this column belongs
* @param style
*            the style bits used to create the column, for applicable style bits
*            see {@link TreeColumn}
* @see TreeColumn#TreeColumn(Tree, int)
*/
    public  TreeViewerColumn(TreeViewer viewer, int style) {
        this(viewer, style, -1);
    }

    /**
* Creates a new viewer column for the given {@link TreeViewer} on a new
* {@link TreeColumn} with the given style bits. The column is added at the
* end of the list of columns.
*
* @param viewer
*            the tree viewer to which this column belongs
* @param style
*            the style bits used to create the column, for applicable style bits
*            see {@link TreeColumn}
* @param index
*            the index at which to place the newly created column
* @see TreeColumn#TreeColumn(Tree, int, int)
*/
    public  TreeViewerColumn(TreeViewer viewer, int style, int index) {
        this(viewer, createColumn(viewer.getTree(), style, index));
    }

    /**
* Creates a new viewer column for the given {@link TreeViewer} on the given
* {@link TreeColumn}.
*
* @param viewer
*            the tree viewer to which this column belongs
* @param column
*            the underlying tree column
*/
    public  TreeViewerColumn(TreeViewer viewer, TreeColumn column) {
        super(viewer, column);
        this.column = column;
    }

    private static TreeColumn createColumn(Tree table, int style, int index) {
        if (index >= 0) {
            return new TreeColumn(table, style, index);
        }
        return new TreeColumn(table, style);
    }

    /**
* @return the underlying SWT column
*/
    public TreeColumn getColumn() {
        return column;
    }
}

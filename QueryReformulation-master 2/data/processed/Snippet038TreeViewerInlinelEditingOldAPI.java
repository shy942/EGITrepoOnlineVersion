/***/
package org.eclipse.jface.snippets.viewers;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

/**
* A simple TreeViewer to demonstrate usage of inline editing
*
* @author Tom Schindl <tom.schindl@bestsolution.at>
*
*/
public class Snippet038TreeViewerInlinelEditingOldAPI {

    private class MyContentProvider implements ITreeContentProvider {

        @Override
        public Object[] getElements(Object inputElement) {
            return ((MyModel) inputElement).child.toArray();
        }

        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            return getElements(parentElement);
        }

        @Override
        public Object getParent(Object element) {
            if (element == null) {
                return null;
            }
            return ((MyModel) element).parent;
        }

        @Override
        public boolean hasChildren(Object element) {
            return ((MyModel) element).child.size() > 0;
        }
    }

    private class MyModel {

        public MyModel parent;

        public List<MyModel> child = new ArrayList();

        public int counter;

        public  MyModel(int counter, MyModel parent) {
            this.parent = parent;
            this.counter = counter;
        }

        @Override
        public String toString() {
            String rv = "Item ";
            if (parent != null) {
                rv = parent.toString() + ".";
            }
            rv += counter;
            return rv;
        }
    }

    private class MyColumnLabelProvider extends ColumnLabelProvider {

        private int columnIndex;

        private Tree tree;

        public  MyColumnLabelProvider(Tree tree, int columnIndex) {
            this.tree = tree;
            this.columnIndex = columnIndex;
        }

        @Override
        public String getText(Object element) {
            return "Column " + tree.getColumnOrder()[columnIndex] + " => " + element.toString();
        }
    }

    private class MyEditingSupport extends EditingSupport {

        public  MyEditingSupport(ColumnViewer viewer) {
            super(viewer);
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return new TextCellEditor((Composite) getViewer().getControl());
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            return ((MyModel) element).counter + "";
        }

        @Override
        protected void setValue(Object element, Object value) {
            ((MyModel) element).counter = Integer.parseInt(value.toString());
            getViewer().update(element, null);
        }
    }

    public  Snippet038TreeViewerInlinelEditingOldAPI(Shell shell) {
        final TreeViewer viewer = new TreeViewer(shell, SWT.FULL_SELECTION);
        createColumnFor(viewer, "Column 1", 0);
        createColumnFor(viewer, "Column 2", 1);
        viewer.setContentProvider(new MyContentProvider());
        viewer.setInput(createModel());
    }

    private MyModel createModel() {
        MyModel root = new MyModel(0, null);
        root.counter = 0;
        MyModel tmp;
        for (int i = 1; i < 10; i++) {
            tmp = new MyModel(i, root);
            root.child.add(tmp);
            for (int j = 1; j < i; j++) {
                tmp.child.add(new MyModel(j, tmp));
            }
        }
        return root;
    }

    private void createColumnFor(TreeViewer viewer, String label, int columnIndex) {
        TreeViewerColumn viewerColumn = new TreeViewerColumn(viewer, SWT.NONE);
        viewerColumn.getColumn().setWidth(200);
        viewerColumn.getColumn().setText(label);
        viewerColumn.setEditingSupport(new MyEditingSupport(viewer));
        viewerColumn.setLabelProvider(new MyColumnLabelProvider(viewer.getTree(), columnIndex));
    }

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        new Snippet038TreeViewerInlinelEditingOldAPI(shell);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}

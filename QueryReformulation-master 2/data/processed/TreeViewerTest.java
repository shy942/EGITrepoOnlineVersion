/***/
package org.eclipse.jface.tests.viewers;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeViewerTest extends AbstractTreeViewerTest {

    public  TreeViewerTest(String name) {
        super(name);
    }

    @Override
    protected StructuredViewer createViewer(Composite parent) {
        fTreeViewer = new TreeViewer(parent);
        fTreeViewer.setContentProvider(new TestModelContentProvider());
        return fTreeViewer;
    }

    @Override
    protected int getItemCount() {
        TestElement first = fRootElement.getFirstChild();
        TreeItem ti = (TreeItem) fViewer.testFindItem(first);
        Tree tree = ti.getParent();
        return tree.getItemCount();
    }

    /**
* getItemCount method comment.
*/
    @Override
    protected int getItemCount(TestElement element) {
        return element.getChildCount();
    }

    @Override
    protected String getItemText(int at) {
        Tree tree = (Tree) fTreeViewer.getControl();
        return tree.getItems()[at].getText();
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(TreeViewerTest.class);
    }
}

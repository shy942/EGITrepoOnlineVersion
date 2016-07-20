/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.tests.viewers.TestElement;
import org.eclipse.jface.tests.viewers.TestModelLazyTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TestLazyVirtualTree extends TestTree {

    @Override
    public Viewer createViewer(Composite parent) {
        Tree tree = new Tree(parent, SWT.VIRTUAL);
        tree.addListener(SWT.SetData, new Listener() {

            private String getPosition(TreeItem item) {
                TreeItem parentItem = item.getParentItem();
                if (parentItem == null) {
                    return "" + item.getParent().indexOf(item);
                }
                return getPosition(parentItem) + "." + parentItem.indexOf(item);
            }

            @Override
            public void handleEvent(Event event) {
                String position = getPosition((TreeItem) event.item);
                System.out.println("updating " + position);
            }
        });
        TreeViewer viewer = new TreeViewer(tree);
        viewer.setContentProvider(new TestModelLazyTreeContentProvider(viewer));
        viewer.setUseHashlookup(true);
        if (fViewer2 == null) {
            fViewer2 = viewer;
        }
        return viewer;
    }

    @Override
    public void setInput(TestElement input) {
        if (fViewer2 != null) {
            Object oldInput = fViewer2.getInput();
            if (oldInput != null) {
                fViewer2.setChildCount(oldInput, 0);
            }
        }
        super.setInput(input);
        if (fViewer2 != null && input != null) {
            fViewer2.setChildCount(input, input.getChildCount());
        }
    }

    public static void main(String[] args) {
        TestBrowser browser = new TestLazyVirtualTree();
        if (args.length > 0 && args[0].equals("-twopanes")) {
            browser.show2Panes();
        }
        browser.setBlockOnOpen(true);
        browser.open(TestElement.createModel(3, 10));
    }
}

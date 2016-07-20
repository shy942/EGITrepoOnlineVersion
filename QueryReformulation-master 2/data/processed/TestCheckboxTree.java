/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.tests.viewers.TestElement;
import org.eclipse.jface.tests.viewers.TestLabelProvider;
import org.eclipse.jface.tests.viewers.TestModelContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

public class TestCheckboxTree extends TestTree {

    CheckboxTreeViewer fCheckboxViewer;

    public  TestCheckboxTree() {
        super();
    }

    public void checkChildren(TestElement element, boolean state) {
        int numChildren = element.getChildCount();
        for (int i = 0; i < numChildren; i++) {
            TestElement child = element.getChildAt(i);
            if (fCheckboxViewer.setChecked(child, state)) {
                checkChildren(child, state);
            }
        }
    }

    @Override
    public Viewer createViewer(Composite parent) {
        CheckboxTreeViewer viewer = new CheckboxTreeViewer(parent);
        viewer.setContentProvider(new TestModelContentProvider());
        viewer.setLabelProvider(new TestLabelProvider());
        viewer.addTreeListener(new ITreeViewerListener() {

            @Override
            public void treeExpanded(TreeExpansionEvent e) {
                handleTreeExpanded((TestElement) e.getElement());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
            }
        });
        viewer.addCheckStateListener(new ICheckStateListener() {

            @Override
            public void checkStateChanged(CheckStateChangedEvent e) {
                checkChildren((TestElement) e.getElement(), e.getChecked());
            }
        });
        fCheckboxViewer = viewer;
        fViewer2 = viewer;
        return viewer;
    }

    public void handleTreeExpanded(TestElement element) {
        // apply the same check recursively to all children
        boolean checked = fCheckboxViewer.getChecked(element);
        int numChildren = element.getChildCount();
        for (int i = 0; i < numChildren; i++) {
            TestElement child = element.getChildAt(i);
            fCheckboxViewer.setChecked(child, checked);
        }
    }

    public static void main(String[] args) {
        TestBrowser browser = new TestCheckboxTree();
        if (args.length > 0 && args[0].equals("-twopanes")) {
            browser.show2Panes();
        }
        browser.setBlockOnOpen(true);
        browser.open(TestElement.createModel(3, 10));
    }
}

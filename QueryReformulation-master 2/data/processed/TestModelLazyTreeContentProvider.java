/***/
package org.eclipse.jface.tests.viewers;

import junit.framework.Assert;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class TestModelLazyTreeContentProvider extends TestModelContentProvider implements ILazyTreeContentProvider {

    private final TreeViewer treeViewer;

    public  TestModelLazyTreeContentProvider(TreeViewer treeViewer) {
        this.treeViewer = treeViewer;
    }

    @Override
    public void updateElement(Object parent, int index) {
        TestElement parentElement = (TestElement) parent;
        if (parentElement.getChildCount() > index) {
            TestElement childElement = parentElement.getChildAt(index);
            treeViewer.replace(parent, index, childElement);
            treeViewer.setChildCount(childElement, childElement.getChildCount());
        }
    }

    @Override
    public Object[] getChildren(Object element) {
        Assert.fail("should not be called on a LazyTreeContentProvider");
        return null;
    }

    @Override
    public Object[] getElements(Object element) {
        Assert.fail("should not be called on a LazyTreeContentProvider");
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        Assert.fail("should not be called on a LazyTreeContentProvider");
        return false;
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, final Object newInput) {
        super.inputChanged(viewer, oldInput, newInput);
    }

    @Override
    public void updateChildCount(Object element, int currentChildCount) {
        treeViewer.setChildCount(element, ((TestElement) element).getChildCount());
    }
}

/***/
package org.eclipse.jface.tests.performance;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
* The RefreshTestTreeContentProvider is the content provider for test trees.
*/
public class RefreshTestTreeContentProvider implements ITreeContentProvider {

    static TestTreeElement[] elements;

    static {
        elements = new TestTreeElement[RefreshTestContentProvider.ELEMENT_COUNT];
        for (int i = 0; i < RefreshTestContentProvider.ELEMENT_COUNT; i++) {
            elements[i] = (new TestTreeElement(i, null));
        }
    }

    public  RefreshTestTreeContentProvider() {
        super();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        return ((TestTreeElement) parentElement).children;
    }

    @Override
    public Object getParent(Object element) {
        return ((TestTreeElement) element).parent;
    }

    @Override
    public boolean hasChildren(Object element) {
        return ((TestTreeElement) element).children.length > 0;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return elements;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}

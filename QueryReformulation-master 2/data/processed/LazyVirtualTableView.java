/***/
package org.eclipse.jface.tests.viewers.interactive;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
* The LazyVirtualTableView is the VirtualTableView with lazy content.
*/
public class LazyVirtualTableView extends VirtualTableView {

    private List elements;

    /**
* Create a new instance of the receiver.
*/
    public  LazyVirtualTableView() {
        super();
        initElements();
    }

    /**
*
*/
    private void initElements() {
        elements = new ArrayList();
        for (int i = 0; i < itemCount; i++) {
            elements.add("Element " + String.valueOf(i));
        }
    }

    @Override
    protected IContentProvider getContentProvider() {
        return new ILazyContentProvider() {

            @Override
            public void updateElement(int index) {
                viewer.replace(elements.get(index), index);
            }

            @Override
            public void dispose() {
            // Do Nothing
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Do nothing.
            }
        };
    }

    protected void doRemove(Object[] selection, int[] selectionIndices) {
        for (int index : selectionIndices) {
            elements.remove(index);
        }
        super.doRemove(selection);
    }

    @Override
    protected void resetInput() {
        viewer.setItemCount(itemCount);
        super.resetInput();
    }
}

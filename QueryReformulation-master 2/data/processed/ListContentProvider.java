/***/
package org.eclipse.ui.tests.api;

import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ListContentProvider implements IStructuredContentProvider {

    /**
* @see IStructuredContentProvider#getElements(Object)
*/
    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof List) {
            return ((List) inputElement).toArray();
        }
        return new Object[0];
    }

    /**
* @see IContentProvider#dispose()
*/
    @Override
    public void dispose() {
    }

    /**
* @see IContentProvider#inputChanged(Viewer, Object, Object)
*/
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}

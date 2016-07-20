/***/
package org.eclipse.jface.internal.databinding.viewers;

import java.util.Collections;
import java.util.List;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.jface.databinding.viewers.ViewerListProperty;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
* @since 3.3
*
*/
public class SelectionProviderMultipleSelectionProperty extends ViewerListProperty {

    private final boolean isPostSelection;

    /**
* Constructor.
*
* @param isPostSelection
*            Whether the post selection or the normal selection is to be
*            observed.
*/
    public  SelectionProviderMultipleSelectionProperty(boolean isPostSelection) {
        this.isPostSelection = isPostSelection;
    }

    @Override
    public Object getElementType() {
        return Object.class;
    }

    @Override
    protected List doGetList(Object source) {
        ISelection selection = ((ISelectionProvider) source).getSelection();
        if (selection instanceof IStructuredSelection) {
            return ((IStructuredSelection) selection).toList();
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    protected void doSetList(Object source, List list, ListDiff diff) {
        doSetList(source, list);
    }

    @Override
    protected void doSetList(Object source, List list) {
        ((ISelectionProvider) source).setSelection(new StructuredSelection(list));
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return new SelectionChangedListener(this, listener, isPostSelection);
    }

    @Override
    public String toString() {
        return //$NON-NLS-1$
        isPostSelection ? //$NON-NLS-1$
        "IPostSelectionProvider.postSelection[]" : //$NON-NLS-1$
        "ISelectionProvider.selection[]";
    }
}

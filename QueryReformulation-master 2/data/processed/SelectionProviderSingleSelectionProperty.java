/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.jface.databinding.viewers.ViewerValueProperty;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;

/**
* @since 3.3
*
*/
public class SelectionProviderSingleSelectionProperty extends ViewerValueProperty {

    private final boolean isPostSelection;

    /**
* Constructor.
*
* @param isPostSelection
*            Whether the post selection or the normal selection is to be
*            observed.
*/
    public  SelectionProviderSingleSelectionProperty(boolean isPostSelection) {
        this.isPostSelection = isPostSelection;
    }

    @Override
    public Object getValueType() {
        return null;
    }

    @Override
    protected Object doGetValue(Object source) {
        ISelection selection = ((ISelectionProvider) source).getSelection();
        if (selection instanceof IStructuredSelection) {
            return ((IStructuredSelection) selection).getFirstElement();
        }
        return null;
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        IStructuredSelection selection = value == null ? StructuredSelection.EMPTY : new StructuredSelection(value);
        if (source instanceof Viewer) {
            ((Viewer) source).setSelection(selection, true);
        } else {
            ((ISelectionProvider) source).setSelection(selection);
        }
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return new SelectionChangedListener(this, listener, isPostSelection);
    }

    @Override
    public String toString() {
        return //$NON-NLS-1$
        isPostSelection ? //$NON-NLS-1$
        "IPostSelectionProvider.postSelection" : //$NON-NLS-1$
        "ISelectionProvider.selection";
    }
}

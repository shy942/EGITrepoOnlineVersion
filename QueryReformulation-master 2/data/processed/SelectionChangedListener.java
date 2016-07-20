/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.NativePropertyListener;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

class SelectionChangedListener extends NativePropertyListener implements ISelectionChangedListener {

    private final boolean isPostSelection;

     SelectionChangedListener(IProperty property, ISimplePropertyListener listener, boolean isPostSelection) {
        super(property, listener);
        this.isPostSelection = isPostSelection;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        fireChange(event.getSource(), null);
    }

    @Override
    public void doAddTo(Object source) {
        if (isPostSelection) {
            ((IPostSelectionProvider) source).addPostSelectionChangedListener(this);
        } else {
            ((ISelectionProvider) source).addSelectionChangedListener(this);
        }
    }

    @Override
    public void doRemoveFrom(Object source) {
        if (isPostSelection) {
            ((IPostSelectionProvider) source).removePostSelectionChangedListener(this);
        } else {
            ((ISelectionProvider) source).removeSelectionChangedListener(this);
        }
    }
}

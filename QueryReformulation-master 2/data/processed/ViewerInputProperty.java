/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.jface.databinding.viewers.ViewerValueProperty;
import org.eclipse.jface.viewers.Viewer;

/**
* @since 3.3
*
*/
public class ViewerInputProperty extends ViewerValueProperty {

    @Override
    public Object getValueType() {
        return null;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((Viewer) source).getInput();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        ((Viewer) source).setInput(value);
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return null;
    }

    protected void doAddListener(Object source, INativePropertyListener listener) {
    }

    protected void doRemoveListener(Object source, INativePropertyListener listener) {
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Viewer.input";
    }
}

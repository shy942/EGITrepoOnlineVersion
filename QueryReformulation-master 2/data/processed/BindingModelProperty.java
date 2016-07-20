/***/
package org.eclipse.core.internal.databinding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;

/**
* @since 3.3
*
*/
public class BindingModelProperty extends SimpleValueProperty {

    @Override
    public Object getValueType() {
        return IObservable.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((Binding) source).getModel();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
    // no setter API
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        // no listener API
        return null;
    }

    protected void doAddListener(Object source, INativePropertyListener listener) {
    }

    protected void doRemoveListener(Object source, INativePropertyListener listener) {
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Binding#model <IObservable>";
    }
}

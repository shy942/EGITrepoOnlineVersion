/***/
package org.eclipse.core.internal.databinding;

import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;

/**
* @since 3.3
*
*/
public final class ValidationStatusProviderValidationStatusProperty extends SimpleValueProperty {

    @Override
    public Object getValueType() {
        return IObservableValue.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((ValidationStatusProvider) source).getValidationStatus();
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
        return "ValidationStatusProvider#validationStatus <IObservableValue>";
    }
}

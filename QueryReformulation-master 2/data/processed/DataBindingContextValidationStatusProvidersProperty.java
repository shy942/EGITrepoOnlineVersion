/***/
package org.eclipse.core.internal.databinding;

import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.property.list.ListProperty;

/**
* @since 3.3
*
*/
public final class DataBindingContextValidationStatusProvidersProperty extends ListProperty {

    @Override
    public Object getElementType() {
        return ValidationStatusProvider.class;
    }

    @Override
    protected List doGetList(Object source) {
        return ((DataBindingContext) source).getValidationStatusProviders();
    }

    @Override
    protected void doSetList(Object source, List list) {
        //$NON-NLS-1$
        throw new UnsupportedOperationException(toString() + " is unmodifiable");
    }

    @Override
    protected void doUpdateList(Object source, ListDiff diff) {
        //$NON-NLS-1$
        throw new UnsupportedOperationException(toString() + " is unmodifiable");
    }

    @Override
    public IObservableList observe(Realm realm, Object source) {
        return ((DataBindingContext) source).getValidationStatusProviders();
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Binding#validationStatusProviders[] <ValidationStatusProvider>";
    }
}

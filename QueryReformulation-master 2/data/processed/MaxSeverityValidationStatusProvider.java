/***/
package org.eclipse.jface.databinding.dialog;

import java.util.Collection;
import java.util.Iterator;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.runtime.IStatus;

/*package*/
class MaxSeverityValidationStatusProvider extends ComputedValue {

    private Collection validationStatusProviders;

    public  MaxSeverityValidationStatusProvider(DataBindingContext dbc) {
        super(ValidationStatusProvider.class);
        this.validationStatusProviders = dbc.getValidationStatusProviders();
    }

    @Override
    protected Object calculate() {
        int maxSeverity = IStatus.OK;
        ValidationStatusProvider maxSeverityProvider = null;
        for (Iterator it = validationStatusProviders.iterator(); it.hasNext(); ) {
            ValidationStatusProvider provider = (ValidationStatusProvider) it.next();
            IStatus status = (IStatus) provider.getValidationStatus().getValue();
            if (status.getSeverity() > maxSeverity) {
                maxSeverity = status.getSeverity();
                maxSeverityProvider = provider;
            }
        }
        return maxSeverityProvider;
    }

    @Override
    public synchronized void dispose() {
        validationStatusProviders = null;
        super.dispose();
    }
}

/***/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.runtime.IStatus;

/**
* Converts an IStatus into a String. The message of the status is the returned
* value.
*
* @since 1.0
*/
public class StatusToStringConverter extends Converter implements IConverter {

    /**
* Constructs a new instance.
*/
    public  StatusToStringConverter() {
        super(IStatus.class, String.class);
    }

    @Override
    public Object convert(Object fromObject) {
        if (fromObject == null) {
            throw new IllegalArgumentException(//$NON-NLS-1$
            "Parameter 'fromObject' was null.");
        }
        IStatus status = (IStatus) fromObject;
        return status.getMessage();
    }
}

/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;

/**
* Class that wraps an object and forwards adapter calls if possible, otherwise
* returns the object. This is used to maintain API compatibility with methods that
* need an IAdaptable but when the operation supports a broader type.
*
* @since 3.2
*/
public class AdaptableForwarder implements IAdaptable {

    private Object element;

    /**
* Create a new instance of the receiver.
* @param element
*/
    public  AdaptableForwarder(Object element) {
        this.element = element;
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return Adapters.adapt(element, adapter);
    }
}

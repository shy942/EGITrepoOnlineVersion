/***/
package org.eclipse.core.databinding.beans;

import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.observable.IObserving;

/**
* Provides access to details of bean observables.
* <p>
* This interface is not meant to be implemented by clients.
* </p>
*
* @since 3.3
*/
public interface IBeanObservable extends IObserving {

    /**
* @return property descriptor of the property being observed,
*         <code>null</code> if the runtime time information was not
*         provided on construction of the observable
*/
    public PropertyDescriptor getPropertyDescriptor();
}

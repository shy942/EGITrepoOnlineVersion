/***/
package org.eclipse.core.databinding.property;

import org.eclipse.core.databinding.observable.IObserving;

/**
* Provides access to the details of property observables
*
* @param <P>
*            specific type of the value property being observed
* @since 1.2
*/
public interface IPropertyObservable<P extends IProperty> extends IObserving {

    /**
* Returns the property being observed
*
* @return the property being observed
*/
    P getProperty();
}

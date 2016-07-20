/***/
package org.eclipse.jface.databinding.conformance.delegate;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
* Abstract implementation of {@link IObservableValueContractDelegate}.
*
* @since 1.1
*/
public abstract class AbstractObservableValueContractDelegate extends AbstractObservableContractDelegate implements IObservableValueContractDelegate {

    /**
* Invokes {@link #createObservableValue(Realm)}.
*
* @param realm
* @return observable
*/
    @Override
    public final IObservable createObservable(Realm realm) {
        return createObservableValue(realm);
    }

    /**
* Default implementation returns <code>null</code>.
*
* @param observable
* @return value type
*/
    @Override
    public Object getValueType(IObservableValue observable) {
        // no op
        return null;
    }

    /**
* Default implementation returns <code>null</code>.
*
* @param observable
* @return value
*/
    @Override
    public Object createValue(IObservableValue observable) {
        // no op
        return null;
    }
}

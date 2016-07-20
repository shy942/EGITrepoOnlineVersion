/***/
package org.eclipse.jface.databinding.conformance.delegate;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.Realm;

/**
* Abstract implementation of {@link IObservableCollectionContractDelegate}.
*
* @since 3.2
*/
public abstract class AbstractObservableCollectionContractDelegate extends AbstractObservableContractDelegate implements IObservableCollectionContractDelegate {

    /**
* Invokes
* {@link IObservableCollectionContractDelegate#createObservableCollection(Realm, int)}
* .
*
* @param realm
* @return observable
*/
    @Override
    public final IObservable createObservable(Realm realm) {
        return createObservableCollection(realm, 0);
    }

    @Override
    public Object createElement(IObservableCollection collection) {
        // no op
        return null;
    }

    @Override
    public Object getElementType(IObservableCollection collection) {
        // no op
        return null;
    }
}

/***/
package org.eclipse.jface.databinding.conformance.delegate;

import org.eclipse.core.databinding.observable.IObservable;

/**
* Abstract implementation of {@link IObservableContractDelegate}.
*
* @since 1.1
*/
public abstract class AbstractObservableContractDelegate implements IObservableContractDelegate {

    @Override
    public void setUp() {
    // no op
    }

    @Override
    public void tearDown() {
    // no op
    }

    @Override
    public void change(IObservable observable) {
    // no op
    }

    @Override
    public void setStale(IObservable observable, boolean stale) {
    // no op
    }
}

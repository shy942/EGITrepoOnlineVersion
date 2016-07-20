/***/
package org.eclipse.core.databinding.observable;

/**
* Interface for observables which decorate other observables.
*
* @noimplement This interface is not intended to be implemented by clients.
*              Clients should instead subclass one of the classes in the
*              framework that implement this interface. Note that direct
*              implementers of this interface outside of the framework will be
*              broken in future releases when methods are added to this
*              interface.
* @since 1.2
*/
public interface IDecoratingObservable extends IObservable {

    /**
* @return the observable that this observable decorates.
*/
    public IObservable getDecorated();
}

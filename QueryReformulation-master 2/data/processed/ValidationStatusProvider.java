/***/
package org.eclipse.core.databinding;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;

/**
* A validation status provider tracks the state of zero or more target
* observables and zero or more model observables and produces a validation
* result.
*
* @since 1.1
*
*/
public abstract class ValidationStatusProvider {

    protected boolean disposed = false;

    /**
* @return an {@link IObservableValue}&lt; {@link IStatus} &gt; containing
*         the current validation status
*/
    public abstract IObservableValue getValidationStatus();

    /**
* Returns an {@link IObservableList} &lt; {@link IObservable} &gt;
* containing the target observables (if any) that are being tracked by this
* validation status provider.
*
* @return an {@link IObservableList} &lt; {@link IObservable} &gt; (may be
*         empty)
*/
    public abstract IObservableList getTargets();

    /**
* Returns an {@link IObservableList} &lt; {@link IObservable} &gt;
* containing the model observables (if any) that are being tracked by this
* validation status provider.
*
* @return an {@link IObservableList} &lt; {@link IObservable} &gt; (may be
*         empty)
*/
    public abstract IObservableList getModels();

    /**
* Disposes of this ValidationStatusProvider. Subclasses may extend, but
* must call super.dispose().
*/
    public void dispose() {
        disposed = true;
    }

    /**
* @return true if the binding has been disposed. false otherwise.
*/
    public boolean isDisposed() {
        return disposed;
    }
}

/***/
package org.eclipse.core.internal.databinding.property;

import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.IObservable;

/**
* @since 3.3
*
*/
public class PropertyObservableUtil {

    /**
* Causes the target observable to be disposed when the source observable is
* disposed.
*
* @param source
*            the source observable
* @param target
*            the target observable
*/
    public static void cascadeDispose(IObservable source, final IObservable target) {
        source.addDisposeListener(new IDisposeListener() {

            @Override
            public void handleDispose(DisposeEvent staleEvent) {
                target.dispose();
            }
        });
    }
}

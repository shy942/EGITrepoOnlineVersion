/***/
package org.eclipse.core.internal.databinding.observable;

/**
* @since 1.0
*
*/
@FunctionalInterface
public interface IStalenessConsumer {

    /**
* @param stale
*
*/
    public void setStale(boolean stale);
}

/***/
package org.eclipse.core.databinding.observable.map;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;

/**
*
* <p>
* This class is thread safe. All state accessing methods must be invoked from
* the {@link Realm#isCurrent() current realm}. Methods for adding and removing
* listeners may be invoked from any thread.
* </p>
*
* @since 1.0
*
* @param <K>
*            type of the keys to the map
* @param <V>
*            type of the values in the map
*
* @deprecated This class is deprecated; use {@link BidiObservableMap} instead.
*/
@Deprecated
public class BidirectionalMap<// OK to ignore warnings in deprecated class
K, V> extends ObservableMap<K, V> {

    private IMapChangeListener<K, V> mapListener = new IMapChangeListener<K, V>() {

        @Override
        public void handleMapChange(MapChangeEvent<? extends K, ? extends V> event) {
            fireMapChange(Diffs.unmodifiableDiff(event.diff));
        }
    };

    /**
* @param wrappedMap
*/
    public  BidirectionalMap(IObservableMap<K, V> wrappedMap) {
        super(wrappedMap.getRealm(), wrappedMap);
        wrappedMap.addMapChangeListener(mapListener);
    }
}

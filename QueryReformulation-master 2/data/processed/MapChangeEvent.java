/***/
package org.eclipse.core.databinding.observable.map;

import org.eclipse.core.databinding.observable.IObservablesListener;
import org.eclipse.core.databinding.observable.ObservableEvent;

/**
* Map change event describing an incremental change of an
* {@link IObservableMap} object.
*
* @param <K>
*            type of the keys to the map
* @param <V>
*            type of the values in the map
* @since 1.0
*
*/
public class MapChangeEvent<K, V> extends ObservableEvent {

    /**
*
*/
    private static final long serialVersionUID = -8092347212410548463L;

    static final Object TYPE = new Object();

    /**
* Description of the change to the source observable map. Listeners must
* not change this field.
*/
    public MapDiff<K, V> diff;

    /**
* Creates a new map change event
*
* @param source
*            the source observable map
* @param diff
*            the map change
*/
    public  MapChangeEvent(IObservableMap<K, V> source, MapDiff<K, V> diff) {
        super(source);
        this.diff = diff;
    }

    /**
* Returns the observable map from which this event originated.
*
* @return the observable map from which this event originated
*/
    @SuppressWarnings("unchecked")
    public IObservableMap<K, V> getObservableMap() {
        return (IObservableMap<K, V>) getSource();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void dispatch(IObservablesListener listener) {
        ((IMapChangeListener<K, V>) listener).handleMapChange(this);
    }

    @Override
    protected Object getListenerType() {
        return TYPE;
    }
}

/***/
package org.eclipse.core.internal.databinding.property;

import java.util.Map;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;

/**
* @param <S>
*            type of the source object
* @param <M>
*            type of the property of the source object this type being the type
*            that has the map as a property
* @param <K>
*            type of the keys to the map
* @param <V>
*            type of the values in the map
* @since 3.3
*
*/
public class ValuePropertyDetailMap<S, M, K, V> extends MapProperty<S, K, V> {

    private final IValueProperty<S, M> masterProperty;

    private final IMapProperty<? super M, K, V> detailProperty;

    /**
* @param masterProperty
* @param detailProperty
*/
    public  ValuePropertyDetailMap(IValueProperty<S, M> masterProperty, IMapProperty<? super M, K, V> detailProperty) {
        this.masterProperty = masterProperty;
        this.detailProperty = detailProperty;
    }

    @Override
    public Object getKeyType() {
        return detailProperty.getKeyType();
    }

    @Override
    public Object getValueType() {
        return detailProperty.getValueType();
    }

    @Override
    protected Map<K, V> doGetMap(S source) {
        M masterValue = masterProperty.getValue(source);
        return detailProperty.getMap(masterValue);
    }

    @Override
    protected void doSetMap(S source, Map<K, V> map) {
        M masterValue = masterProperty.getValue(source);
        detailProperty.setMap(masterValue, map);
    }

    @Override
    protected void doUpdateMap(S source, MapDiff<K, V> diff) {
        M masterValue = masterProperty.getValue(source);
        detailProperty.updateMap(masterValue, diff);
    }

    @Override
    public IObservableMap<K, V> observe(Realm realm, S source) {
        IObservableValue<M> masterValue;
        ObservableTracker.setIgnore(true);
        try {
            masterValue = masterProperty.observe(realm, source);
        } finally {
            ObservableTracker.setIgnore(false);
        }
        IObservableMap<K, V> detailMap = detailProperty.observeDetail(masterValue);
        PropertyObservableUtil.cascadeDispose(detailMap, masterValue);
        return detailMap;
    }

    @Override
    public <U extends S> IObservableMap<K, V> observeDetail(IObservableValue<U> master) {
        IObservableValue<M> masterValue;
        ObservableTracker.setIgnore(true);
        try {
            masterValue = masterProperty.observeDetail(master);
        } finally {
            ObservableTracker.setIgnore(false);
        }
        IObservableMap<K, V> detailMap = detailProperty.observeDetail(masterValue);
        PropertyObservableUtil.cascadeDispose(detailMap, masterValue);
        return detailMap;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return masterProperty + " => " + detailProperty;
    }
}

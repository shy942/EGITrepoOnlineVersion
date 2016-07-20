/***/
package org.eclipse.core.internal.databinding.property.map;

import java.util.Map;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.map.SimpleMapProperty;

/**
* @param <K>
*            type of the keys to the map
* @param <V>
*            type of the values in the map
* @since 3.3
*
*/
public final class SelfMapProperty<K, V> extends SimpleMapProperty<Map<K, V>, K, V> {

    private final Object keyType;

    private final Object valueType;

    /**
* @param keyType
* @param valueType
*/
    public  SelfMapProperty(Object keyType, Object valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public Object getKeyType() {
        return keyType;
    }

    @Override
    public Object getValueType() {
        return valueType;
    }

    @Override
    protected Map<K, V> doGetMap(Map<K, V> source) {
        return source;
    }

    @Override
    protected void doSetMap(Map<K, V> source, Map<K, V> map, MapDiff<K, V> diff) {
        doUpdateMap(source, diff);
    }

    @Override
    protected void doUpdateMap(Map<K, V> source, MapDiff<K, V> diff) {
        diff.applyTo(source);
    }

    @Override
    public INativePropertyListener<Map<K, V>> adaptListener(ISimplePropertyListener<Map<K, V>, MapDiff<K, V>> listener) {
        // no listener API
        return null;
    }

    protected void doAddListener(Object source, INativePropertyListener<Map<K, V>> listener) {
    }

    protected void doRemoveListener(Object source, INativePropertyListener<Map<K, V>> listener) {
    }
}

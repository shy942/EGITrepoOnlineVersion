/***/
package org.eclipse.core.internal.databinding.property;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.MapProperty;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.internal.databinding.identity.IdentityMap;

/**
* @param <S>
*            type of the source object
* @param <M>
*            type of the elements in the master set
* @param <T>
*            type of the elements in the list, being the type of the value of
*            the detail property
* @since 3.3
*
*/
public class SetPropertyDetailValuesMap<S, M, T> extends MapProperty<S, M, T> {

    private final ISetProperty<S, M> masterProperty;

    private final IValueProperty<? super M, T> detailProperty;

    /**
* @param masterProperty
* @param detailProperty
*/
    public  SetPropertyDetailValuesMap(ISetProperty<S, M> masterProperty, IValueProperty<? super M, T> detailProperty) {
        this.masterProperty = masterProperty;
        this.detailProperty = detailProperty;
    }

    @Override
    public Object getKeyType() {
        return masterProperty.getElementType();
    }

    @Override
    public Object getValueType() {
        return detailProperty.getValueType();
    }

    @Override
    protected Map<M, T> doGetMap(S source) {
        Set<M> set = masterProperty.getSet(source);
        Map<M, T> map = new IdentityMap();
        for (Iterator<M> it = set.iterator(); it.hasNext(); ) {
            M key = it.next();
            map.put(key, detailProperty.getValue(key));
        }
        return map;
    }

    @Override
    protected void doUpdateMap(S source, MapDiff<M, T> diff) {
        if (!diff.getAddedKeys().isEmpty())
            //$NON-NLS-1$
            throw new UnsupportedOperationException(toString() + " does not support entry additions");
        if (!diff.getRemovedKeys().isEmpty())
            //$NON-NLS-1$
            throw new UnsupportedOperationException(toString() + " does not support entry removals");
        for (M key : diff.getChangedKeys()) {
            T newValue = diff.getNewValue(key);
            detailProperty.setValue(key, newValue);
        }
    }

    @Override
    public IObservableMap<M, T> observe(Realm realm, S source) {
        IObservableSet<M> masterSet;
        ObservableTracker.setIgnore(true);
        try {
            masterSet = masterProperty.observe(realm, source);
        } finally {
            ObservableTracker.setIgnore(false);
        }
        IObservableMap<M, T> detailMap = detailProperty.observeDetail(masterSet);
        PropertyObservableUtil.cascadeDispose(detailMap, masterSet);
        return detailMap;
    }

    @Override
    public <U extends S> IObservableMap<M, T> observeDetail(IObservableValue<U> master) {
        IObservableSet<M> masterSet;
        ObservableTracker.setIgnore(true);
        try {
            masterSet = masterProperty.observeDetail(master);
        } finally {
            ObservableTracker.setIgnore(false);
        }
        IObservableMap<M, T> detailMap = detailProperty.observeDetail(masterSet);
        PropertyObservableUtil.cascadeDispose(detailMap, masterSet);
        return detailMap;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return masterProperty + " => " + detailProperty;
    }
}

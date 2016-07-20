/***/
package org.eclipse.core.internal.databinding.property;

import java.util.Set;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.core.databinding.property.set.SetProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;

/**
* @param <S>
*            type of the source object
* @param <M>
*            type of the property of the source object this type being the type
*            that has the set as a property
* @param <T>
*            type of the elements in the set, being the type of the value of
*            the detail property
* @since 3.3
*
*/
public class ValuePropertyDetailSet<S, M, T> extends SetProperty<S, T> {

    private IValueProperty<S, M> masterProperty;

    private ISetProperty<? super M, T> detailProperty;

    /**
* @param masterProperty
* @param detailProperty
*/
    public  ValuePropertyDetailSet(IValueProperty<S, M> masterProperty, ISetProperty<? super M, T> detailProperty) {
        this.masterProperty = masterProperty;
        this.detailProperty = detailProperty;
    }

    @Override
    public Object getElementType() {
        return detailProperty.getElementType();
    }

    @Override
    protected Set<T> doGetSet(S source) {
        M masterValue = masterProperty.getValue(source);
        return detailProperty.getSet(masterValue);
    }

    @Override
    protected void doSetSet(S source, Set<T> set) {
        M masterValue = masterProperty.getValue(source);
        detailProperty.setSet(masterValue, set);
    }

    @Override
    protected void doUpdateSet(S source, SetDiff<T> diff) {
        M masterValue = masterProperty.getValue(source);
        detailProperty.updateSet(masterValue, diff);
    }

    @Override
    public IObservableSet<T> observe(Realm realm, S source) {
        IObservableValue<M> masterValue;
        ObservableTracker.setIgnore(true);
        try {
            masterValue = masterProperty.observe(realm, source);
        } finally {
            ObservableTracker.setIgnore(false);
        }
        IObservableSet<T> detailSet = detailProperty.observeDetail(masterValue);
        PropertyObservableUtil.cascadeDispose(detailSet, masterValue);
        return detailSet;
    }

    @Override
    public <U extends S> IObservableSet<T> observeDetail(IObservableValue<U> master) {
        IObservableValue<M> masterValue;
        ObservableTracker.setIgnore(true);
        try {
            masterValue = masterProperty.observeDetail(master);
        } finally {
            ObservableTracker.setIgnore(false);
        }
        IObservableSet<T> detailSet = detailProperty.observeDetail(masterValue);
        PropertyObservableUtil.cascadeDispose(detailSet, masterValue);
        return detailSet;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return masterProperty + " => " + detailProperty;
    }
}

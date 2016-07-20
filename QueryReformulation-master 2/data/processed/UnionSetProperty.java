/***/
package org.eclipse.core.databinding.property.set;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.observable.set.UnionSet;
import org.eclipse.core.internal.databinding.property.PropertyObservableUtil;

/**
* A set property for observing the union of multiple set properties a combined
* set.
*
* @param <S>
*            type of the source object
* @param <E>
*            type of the elements in the set
* @since 1.2
*/
public class UnionSetProperty<S, E> extends SetProperty<S, E> {

    private final ISetProperty<S, E>[] properties;

    private final Object elementType;

    /**
* @param properties
*/
    public  UnionSetProperty(ISetProperty<S, E>[] properties) {
        this(properties, null);
    }

    /**
* @param properties
* @param elementType
*/
    public  UnionSetProperty(ISetProperty<S, E>[] properties, Object elementType) {
        this.properties = properties;
        this.elementType = elementType;
    }

    @Override
    public Object getElementType() {
        return elementType;
    }

    @Override
    protected Set<E> doGetSet(S source) {
        Set<E> set = new HashSet();
        for (int i = 0; i < properties.length; i++) set.addAll(properties[i].getSet(source));
        return set;
    }

    @Override
    protected void doSetSet(S source, Set<E> set) {
        //$NON-NLS-1$
        throw new UnsupportedOperationException("UnionSetProperty is unmodifiable");
    }

    @Override
    protected void doUpdateSet(S source, SetDiff<E> diff) {
        //$NON-NLS-1$
        throw new UnsupportedOperationException("UnionSetProperty is unmodifiable");
    }

    @Override
    public IObservableSet<E> observe(Realm realm, S source) {
        Set<IObservableSet<? extends E>> sets = new HashSet<IObservableSet<? extends E>>(properties.length);
        for (ISetProperty<S, E> property : properties) {
            sets.add(property.observe(realm, source));
        }
        IObservableSet<E> unionSet = new UnionSet(sets, elementType);
        for (IObservableSet<? extends E> set : sets) {
            PropertyObservableUtil.cascadeDispose(unionSet, set);
        }
        return unionSet;
    }
}

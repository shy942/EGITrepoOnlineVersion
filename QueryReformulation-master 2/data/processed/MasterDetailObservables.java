/***/
package org.eclipse.core.databinding.observable.masterdetail;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableList;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableMap;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableSet;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableValue;
import org.eclipse.core.internal.databinding.observable.masterdetail.ListDetailValueObservableList;
import org.eclipse.core.internal.databinding.observable.masterdetail.MapDetailValueObservableMap;
import org.eclipse.core.internal.databinding.observable.masterdetail.SetDetailValueObservableMap;

/**
* Allows for the observation of an attribute, the detail, of an observable
* representing selection or another transient instance, the master.
*
* @since 1.0
*/
public class MasterDetailObservables {

    /**
* Creates a detail observable value from a master observable value and a
* factory. This can be used to create observable values that represent a
* property of a selected object in a table.
*
* @param <M>
*            type of the master observable
* @param <T>
*            type of the inner detail observable
* @param master
*            the observable value to track
* @param detailFactory
*            a factory for creating {@link IObservableValue} instances
*            given a current value of the master
* @param detailType
*            the value type of the detail observable value, typically of
*            type java.lang.Class and can be <code>null</code>
* @return an observable value of the given value type that, for any current
*         value of the given master value, behaves like the observable
*         value created by the factory for that current value.
*/
    public static <M, T> IObservableValue<T> detailValue(IObservableValue<M> master, IObservableFactory<? super M, IObservableValue<T>> detailFactory, Object detailType) {
        return new DetailObservableValue(master, detailFactory, detailType);
    }

    /**
* Creates a detail observable list from a master observable value and a
* factory. This can be used to create observable lists that represent a
* list property of a selected object in a table.
*
* @param <M>
*            type of the master observable
* @param <E>
*            type of the elements in the inner observable set
* @param master
*            the observable value to track
* @param detailFactory
*            a factory for creating {@link IObservableList} instances given
*            a current value of the master
* @param detailElementType
*            the element type of the detail observable list, typically of
*            type java.lang.Class and can be <code>null</code>
* @return an observable list with the given element type that, for any
*         current value of the given master value, behaves like the
*         observable list created by the factory for that current value.
*/
    public static <M, E> IObservableList<E> detailList(IObservableValue<M> master, IObservableFactory<? super M, IObservableList<E>> detailFactory, Object detailElementType) {
        return new DetailObservableList(detailFactory, master, detailElementType);
    }

    /**
* Creates a detail observable set from a master observable value and a
* factory. This can be used to create observable sets that represent a set
* property of a selected object in a table.
*
* @param <M>
*            type of the master observable
* @param <E>
*            type of the elements in the inner observable set
* @param master
*            the observable value to track
* @param detailFactory
*            a factory for creating {@link IObservableSet} instances given
*            a current value of the master
* @param detailElementType
*            the element type of the detail observable set, typically of
*            type java.lang.Class and can be <code>null</code>
* @return an observable set with the given element type that, for any
*         current value of the given master value, behaves like the
*         observable set created by the factory for that current value.
*/
    public static <M, E> IObservableSet<E> detailSet(IObservableValue<M> master, IObservableFactory<? super M, IObservableSet<E>> detailFactory, Object detailElementType) {
        return new DetailObservableSet(detailFactory, master, detailElementType);
    }

    /**
* Creates a detail observable map from a master observable value and a
* factory. This can be used to create observable maps that represent a map
* property of a selected object in a table.
*
* @param <M>
*            type of the master observable
* @param <K>
*            type of the keys to the inner observable map
* @param <V>
*            type of the values in the inner observable map
* @param master
*            the observable value to track
* @param detailFactory
*            a factory for creating {@link IObservableMap} instances given
*            a current value of the master
* @return an observable map that, for any current value of the given master
*         value, behaves like the observable map created by the factory for
*         that current value.
* @since 1.1
*/
    public static <M, K, V> IObservableMap<K, V> detailMap(IObservableValue<M> master, IObservableFactory<M, IObservableMap<K, V>> detailFactory) {
        return detailMap(master, detailFactory, null, null);
    }

    /**
* Creates a detail observable map from a master observable value and a
* factory. This can be used to create observable maps that represent a map
* property of a selected object in a table.
*
* @param <M>
*            type of the master observable
* @param <K>
*            type of the keys to the inner observable map
* @param <V>
*            type of the values in the inner observable map
* @param master
*            the observable value to track
* @param detailFactory
*            a factory for creating {@link IObservableMap} instances given
*            a current value of the master
* @param detailKeyType
*            the element type of the detail observable map's key set,
*            typically of type java.lang.Class and can be <code>null</code>
* @param detailValueType
*            the element type of the detail observable map's values
*            collection, typically of type java.lang.Class and can be
*            <code>null</code>
* @return an observable map that, for any current value of the given master
*         value, behaves like the observable map created by the factory for
*         that current value.
* @since 1.2
*/
    public static <M, K, V> IObservableMap<K, V> detailMap(IObservableValue<M> master, IObservableFactory<? super M, IObservableMap<K, V>> detailFactory, Object detailKeyType, Object detailValueType) {
        return new DetailObservableMap(detailFactory, master, detailKeyType, detailValueType);
    }

    /**
* Returns a detail observable list where each element is the detail value
* of the element in the master observable list. The provided factory is
* used to create the detail observable values for every master element
* which then define the elements of the detail list. The detail list
* resides in the same realm as the given master list.
*
* <p>
* Note that since the values of the returned list are detail values of the
* elements of the master list, the only modifications supported are through
* the {@link IObservableList#set(int, Object)} method. Modifications made
* through the returned list are made through the detail observables created
* by the specified observable factory.
* </p>
*
* @param <M>
*            type of the master observables in the master list
* @param <E>
*            type of the detail elements
* @param masterList
*            The master observable list.
* @param detailFactory
*            The factory for creating {@link IObservableValue} instances
*            for the elements of the master list which then define the
*            elements of the new detail list.
* @param detailType
*            The value type of the detail values, typically of type
*            <code>java.lang.Class</code>. May be <code>null</code>.
* @return A detail observable list with elements which correspond to the
*         detail values of the elements of the master list.
*
* @since 1.4
*/
    public static <M, E> IObservableList<E> detailValues(IObservableList<M> masterList, IObservableFactory<? super M, IObservableValue<E>> detailFactory, Object detailType) {
        return new ListDetailValueObservableList(masterList, detailFactory, detailType);
    }

    /**
* Returns a detail observable map where the map's key set is the same as
* the given observable set, and where each value is the detail value of the
* element in the master observable set. The provided factory is used to
* create the detail observable values for every master key which then
* define the values of the detail map. The detail map resides in the same
* realm as the given master set.
*
* <p>
* Note that since the values of the returned map are detail values of the
* elements of the master set, the only modifications supported are through
* the {@link IObservableMap#put(Object, Object)} and
* {@link IObservableMap#putAll(java.util.Map)} methods. Therefore, the
* returned map does not add entries for elements not already contained in
* the master set. Modifications made through the returned detail map are
* made through the detail observables created by the specified observable
* factory.
* </p>
*
* @param <M>
*            type of the master observables in the master set
* @param <E>
*            type of the detail elements
* @param masterSet
*            The master observable set.
* @param detailFactory
*            The factory for creating {@link IObservableValue} instances
*            for the elements of the master set which then define the
*            values of the new detail map.
* @param detailType
*            The value type of the detail values, typically of type
*            <code>java.lang.Class</code>. May be <code>null</code>.
* @return A detail observable map with the given master set as key set and
*         with values which correspond to the detail values of the elements
*         of the master set.
*
* @since 1.4
*/
    public static <M, E> IObservableMap<M, E> detailValues(IObservableSet<M> masterSet, IObservableFactory<? super M, IObservableValue<E>> detailFactory, Object detailType) {
        return new SetDetailValueObservableMap(masterSet, detailFactory, detailType);
    }

    /**
* Returns a detail observable map where the map's key set is the same as
* the one of the given master observable map, and where each value is the
* detail value of the corresponding value in the master observable map. The
* provided factory is used to create the detail observable values for every
* master value which then define the values of the detail map. The detail
* map resides in the same realm as the given master map.
*
* <p>
* Note that since the values of the returned map are detail values of the
* values of the master map, the only modifications supported are through
* the {@link IObservableMap#put(Object, Object)} and
* {@link IObservableMap#putAll(java.util.Map)} methods. Therefore, the
* returned map does not add entries for keys not already contained in the
* master map's key set. Modifications made through the returned detail map
* are made through the detail observables created by the specified
* observable factory.
* </p>
*
* @param <K>
*            type of the keys (the keys to both the given master observable
*            map and the keys to the returned detail map, both of which are
*            the same set of keys)
* @param <M>
*            type of the master observables in the master set, being the
*            values of the given master observable map
* @param <E>
*            type of the detail elements, being the values of the returned
*            detail map
* @param masterMap
*            The master observable map.
* @param detailFactory
*            The factory for creating {@link IObservableValue} instances
*            for the values of the master map which then define the values
*            of the new detail map.
* @param detailType
*            The value type of the detail values, typically of type
*            <code>java.lang.Class</code>. May be <code>null</code>.
* @return A detail observable map with the same key set as the given master
*         observable map and with values which correspond to the detail
*         values of the values of the master map.
*
* @since 1.4
*/
    public static <K, M, E> IObservableMap<K, E> detailValues(IObservableMap<K, M> masterMap, IObservableFactory<? super M, IObservableValue<E>> detailFactory, Object detailType) {
        return new MapDetailValueObservableMap(masterMap, detailFactory, detailType);
    }
}

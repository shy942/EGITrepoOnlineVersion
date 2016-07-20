/***/
package org.eclipse.core.databinding.observable.set;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;

/**
* Observable set backed by an observable list. The wrapped list must not
* contain duplicate elements.
*
* <p>
* This class is thread safe. All state accessing methods must be invoked from
* the {@link Realm#isCurrent() current realm}. Methods for adding and removing
* listeners may be invoked from any thread.
* </p>
*
* @param <E>
*            the type of elements in the collection
* @since 1.0
*
*/
public class ListToSetAdapter<E> extends ObservableSet<E> {

    private final IObservableList<E> list;

    private IListChangeListener<E> listener = new IListChangeListener<E>() {

        @Override
        public void handleListChange(ListChangeEvent<? extends E> event) {
            Set<E> added = new HashSet();
            Set<E> removed = new HashSet();
            ListDiffEntry<? extends E>[] differences = event.diff.getDifferences();
            for (ListDiffEntry<? extends E> entry : differences) {
                E element = entry.getElement();
                if (entry.isAddition()) {
                    if (wrappedSet.add(element)) {
                        if (!removed.remove(element))
                            added.add(element);
                    }
                } else {
                    if (wrappedSet.remove(element)) {
                        removed.add(element);
                        added.remove(element);
                    }
                }
            }
            fireSetChange(Diffs.createSetDiff(added, removed));
        }
    };

    /**
* @param list
*/
    public  ListToSetAdapter(IObservableList<E> list) {
        super(list.getRealm(), new HashSet<E>(), list.getElementType());
        this.list = list;
        wrappedSet.addAll(list);
        this.list.addListChangeListener(listener);
    }

    @Override
    public synchronized void dispose() {
        super.dispose();
        if (list != null && listener != null) {
            list.removeListChangeListener(listener);
            listener = null;
        }
    }
}

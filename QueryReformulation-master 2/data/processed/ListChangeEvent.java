/***/
package org.eclipse.core.databinding.observable.list;

import org.eclipse.core.databinding.observable.IObservablesListener;
import org.eclipse.core.databinding.observable.ObservableEvent;

/**
* List change event describing an incremental change of an
* {@link IObservableList} object.
*
* @param <E>
*            the type of the elements in this change event
*
* @since 1.0
*/
public class ListChangeEvent<E> extends ObservableEvent {

    /**
*
*/
    private static final long serialVersionUID = -9154315534258776672L;

    static final Object TYPE = new Object();

    /**
* Description of the change to the source observable list. Listeners must
* not change this field.
*/
    public ListDiff<E> diff;

    /**
* Always identical to <code>EventObject.source</code> but the type
* information is maintained.
*/
    private IObservableList<E> typedSource;

    /**
* Creates a new list change event.
*
* @param source
*            the source observable list
* @param diff
*            the list change
*/
    public  ListChangeEvent(IObservableList<E> source, ListDiff<E> diff) {
        super(source);
        this.typedSource = source;
        this.diff = diff;
    }

    /**
* Returns the observable list from which this event originated.
*
* @return the observable list from which this event originated
*/
    public IObservableList<E> getObservableList() {
        return typedSource;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void dispatch(IObservablesListener listener) {
        ((IListChangeListener<E>) listener).handleListChange(this);
    }

    @Override
    protected Object getListenerType() {
        return TYPE;
    }
}

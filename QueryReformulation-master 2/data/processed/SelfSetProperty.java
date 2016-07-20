/***/
package org.eclipse.core.internal.databinding.property.set;

import java.util.Set;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.set.SimpleSetProperty;

/**
* @param <E>
*            type of the elements in the set
* @since 3.3
*
*/
public final class SelfSetProperty<E> extends SimpleSetProperty<Set<E>, E> {

    private final Object elementType;

    /**
* @param elementType
*/
    public  SelfSetProperty(Object elementType) {
        this.elementType = elementType;
    }

    @Override
    public Object getElementType() {
        return elementType;
    }

    @Override
    protected Set<E> doGetSet(Set<E> source) {
        return source;
    }

    @Override
    protected void doSetSet(Set<E> source, Set<E> set, SetDiff<E> diff) {
        diff.applyTo(source);
    }

    @Override
    public INativePropertyListener<Set<E>> adaptListener(ISimplePropertyListener<Set<E>, SetDiff<E>> listener) {
        // no listener API
        return null;
    }

    protected void doAddListener(Object source, INativePropertyListener<Set<E>> listener) {
    }

    protected void doRemoveListener(Set<E> source, INativePropertyListener<Set<E>> listener) {
    }
}

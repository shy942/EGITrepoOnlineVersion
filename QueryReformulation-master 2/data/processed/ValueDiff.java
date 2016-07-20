/***/
package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IDiff;

/**
* @param <T>
*            the type of value being observed
* @since 1.0
*
*/
public abstract class ValueDiff<T> implements IDiff {

    /**
* Creates a value diff.
*/
    public  ValueDiff() {
    }

    /**
* @return the old value
*/
    public abstract T getOldValue();

    /**
* @return the new value
*/
    public abstract T getNewValue();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ValueDiff) {
            ValueDiff<?> val = (ValueDiff<?>) obj;
            return Diffs.equals(val.getNewValue(), getNewValue()) && Diffs.equals(val.getOldValue(), getOldValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Object nv = getNewValue();
        Object ov = getOldValue();
        result = prime * result + ((nv == null) ? 0 : nv.hashCode());
        result = prime * result + ((ov == null) ? 0 : ov.hashCode());
        return result;
    }

    /**
* @see java.lang.Object#toString()
*/
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()).append(//$NON-NLS-1$
        "{oldValue [").append(//$NON-NLS-1$
        getOldValue() != null ? getOldValue().toString() : "null").append(//$NON-NLS-1$
        "], newValue [").append(//$NON-NLS-1$
        getNewValue() != null ? getNewValue().toString() : "null").append(//$NON-NLS-1$
        "]}");
        return buffer.toString();
    }
}

/***/
package org.eclipse.core.internal.databinding.identity;

/**
* Used for wrapping objects that define their own implementations of equals()
* and hashCode() when putting them in sets or hashmaps to ensure identity
* comparison.
*
* @param <T>
*            the type of the object being wrapped
* @since 1.0
*
*/
public class IdentityWrapper<T> {

    /**
* @param <T>
*            the type of the object being wrapped
* @param o
*            the object to wrap
* @return an IdentityWrapper wrapping the specified object
*/
    public static <T> IdentityWrapper<T> wrap(T o) {
        return o == null ? new IdentityWrapper<T>(null) : new IdentityWrapper<T>(o);
    }

    final T o;

    /**
* @param o
*/
    private  IdentityWrapper(T o) {
        this.o = o;
    }

    /**
* @return the unwrapped object
*/
    public T unwrap() {
        return o;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != IdentityWrapper.class) {
            return false;
        }
        return o == ((IdentityWrapper<?>) obj).o;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(o);
    }
}

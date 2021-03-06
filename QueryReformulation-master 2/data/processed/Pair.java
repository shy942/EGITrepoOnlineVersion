/***/
package org.eclipse.core.internal.databinding;

/**
* Class Pair.  Represents a mathematical pair of objects (a, b).
* @since 1.0
*/
public class Pair {

    /**
* a in the pair (a, b)
*/
    public final Object a;

    /**
* b in the pair (a, b)
*/
    public final Object b;

    /**
* Construct a Pair(a, b)
*
* @param a a in the pair (a, b)
* @param b b in the pair (a, b)
*/
    public  Pair(Object a, Object b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((a == null) ? 0 : a.hashCode());
        result = prime * result + ((b == null) ? 0 : b.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (a == null) {
            if (other.a != null)
                return false;
        } else if (!a.equals(other.a))
            return false;
        if (b == null) {
            if (other.b != null)
                return false;
        } else if (!b.equals(other.b))
            return false;
        return true;
    }
}

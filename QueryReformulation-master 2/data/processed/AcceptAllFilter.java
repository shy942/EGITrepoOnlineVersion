/***/
package org.eclipse.jface.viewers;

/**
* Filter that accepts everything. Available as a singleton since having
* more than one instance would be wasteful.
*
* @since 3.1
*/
public final class AcceptAllFilter implements IFilter {

    /**
* Returns the singleton instance of AcceptAllFilter
*
* @return the singleton instance of AcceptAllFilter
*/
    public static IFilter getInstance() {
        return singleton;
    }

    /**
* The singleton instance
*/
    private static IFilter singleton = new AcceptAllFilter();

    @Override
    public boolean select(Object toTest) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof AcceptAllFilter;
    }
}

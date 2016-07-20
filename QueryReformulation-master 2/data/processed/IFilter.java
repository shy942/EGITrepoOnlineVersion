/***/
package org.eclipse.jface.viewers;

/**
* Interface for filters. Can accept or reject items.
*
* @since 3.1
*/
@FunctionalInterface
public interface IFilter {

    /**
* Determines if the given object passes this filter.
*
* @param toTest object to compare against the filter
*
* @return <code>true</code> if the object is accepted by the filter.
*/
    public boolean select(Object toTest);
}

/***/
package org.eclipse.ui.internal.preferences;

/**
* @since 3.1
*/
public interface IDynamicPropertyMap extends IPropertyMap {

    /**
* Attaches a property map listener to this map. The listener will be notified
* whenever one of the properties in the map is changed, added, or removed.
*
* @param listener
* @since 3.1
*/
    public void addListener(IPropertyMapListener listener);

    /**
* Attaches a listener that will receive notifications when any
* of the given properties change. If an identical listener is already registered,
* then this will add additional IDs to the set of properties being monitored
* by the given listener.
*
* @param listener
* @param propertyIds
* @since 3.1
*/
    public void addListener(String[] propertyIds, IPropertyMapListener listener);

    /**
* Removes a property map listener from this map . The listener will no longer
* be notified whenever one of the properties in the map is changed, added, or removed.
*
* @param listener
* @since 3.1
*/
    public void removeListener(IPropertyMapListener listener);
}

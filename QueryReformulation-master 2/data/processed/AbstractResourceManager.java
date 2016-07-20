/***/
package org.eclipse.jface.resource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
* Abstract implementation of ResourceManager. Maintains reference counts for all previously
* allocated SWT resources. Delegates to the abstract method allocate(...) the first time a resource
* is referenced and delegates to the abstract method deallocate(...) the last time a reference is
* removed.
*
* @since 3.1
*/
abstract class AbstractResourceManager extends ResourceManager {

    /**
* Map of ResourceDescriptor onto RefCount. (null when empty)
*/
    private HashMap<DeviceResourceDescriptor, RefCount> map = null;

    /**
* Holds a reference count for a previously-allocated resource
*/
    private static class RefCount {

        Object resource;

        int count = 1;

         RefCount(Object resource) {
            this.resource = resource;
        }
    }

    /**
* Called the first time a resource is requested. Should allocate and return a resource
* of the correct type.
*
* @since 3.1
*
* @param descriptor identifier for the resource to allocate
* @return the newly allocated resource
* @throws DeviceResourceException Thrown when allocation of an SWT device resource fails
*/
    protected abstract Object allocate(DeviceResourceDescriptor descriptor) throws DeviceResourceException;

    /**
* Called the last time a resource is dereferenced. Should release any resources reserved by
* allocate(...).
*
* @since 3.1
*
* @param resource resource being deallocated
* @param descriptor identifier for the resource
*/
    protected abstract void deallocate(Object resource, DeviceResourceDescriptor descriptor);

    @Override
    public final Object create(DeviceResourceDescriptor descriptor) throws DeviceResourceException {
        // Lazily allocate the map
        if (map == null) {
            map = new HashMap();
        }
        // Get the current reference count
        RefCount count = map.get(descriptor);
        if (count != null) {
            // If this resource already exists, increment the reference count and return
            // the existing resource.
            count.count++;
            return count.resource;
        }
        // Allocate and return a new resource (with ref count = 1)
        Object resource = allocate(descriptor);
        count = new RefCount(resource);
        map.put(descriptor, count);
        return resource;
    }

    @Override
    public final void destroy(DeviceResourceDescriptor descriptor) {
        // If the map is empty (null) then there are no resources to dispose
        if (map == null) {
            return;
        }
        // Find the existing resource
        RefCount count = map.get(descriptor);
        if (count != null) {
            // If the resource exists, decrement the reference count.
            count.count--;
            if (count.count == 0) {
                // If this was the last reference, deallocate it.
                deallocate(count.resource, descriptor);
                map.remove(descriptor);
            }
        }
        // Null out the map when empty to save a small amount of memory
        if (map.isEmpty()) {
            map = null;
        }
    }

    /**
* Deallocates any resources allocated by this registry that have not yet been
* deallocated.
*
* @since 3.1
*/
    @Override
    public void dispose() {
        super.dispose();
        if (map == null) {
            return;
        }
        Collection<Entry<DeviceResourceDescriptor, RefCount>> entries = map.entrySet();
        for (Iterator<Entry<DeviceResourceDescriptor, RefCount>> iter = entries.iterator(); iter.hasNext(); ) {
            Entry<DeviceResourceDescriptor, RefCount> next = iter.next();
            Object key = next.getKey();
            RefCount val = next.getValue();
            deallocate(val.resource, (DeviceResourceDescriptor) key);
        }
        map = null;
    }

    @Override
    public Object find(DeviceResourceDescriptor descriptor) {
        if (map == null) {
            return null;
        }
        RefCount refCount = map.get(descriptor);
        if (refCount == null)
            return null;
        return refCount.resource;
    }
}
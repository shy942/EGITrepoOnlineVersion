/***/
package org.eclipse.e4.ui.css.core.resources;

/**
* Interface Resources Registry to cache Resources and dispose it if need.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface IResourcesRegistry {

    /**
* Return resource type of <code>type</code> stored into cache with
* <code>key</code>.
*
* @param type
*            Resource type like Font, Color, Cursor, Image
* @param key
* @return
*/
    public Object getResource(Object type, Object key);

    /**
* Register into cache the resource <code>resource</code> type of
* <code>type</code> with key <code>key</code>.
*
* @param type
*            Resource type like Font, Color, Cursor, Image
* @param key
* @param resource
*/
    public void registerResource(Object type, Object key, Object resource);

    /**
* Unregister from cache the resource <code>resource</code> type of
* <code>type</code> with key <code>key</code>.
*
* @param type
* @param key
*/
    public void unregisterResource(Object type, Object key);

    /**
* Dispose all resources stored into cache.
*/
    public void dispose();
}

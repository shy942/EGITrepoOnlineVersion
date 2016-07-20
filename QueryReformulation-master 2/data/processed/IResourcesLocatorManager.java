/***/
package org.eclipse.e4.ui.css.core.util.resources;

/**
* Resources locator manage to register/unregister {@link IResourceLocator}.
*/
public interface IResourcesLocatorManager extends IResourceLocator {

    /**
* Register <code>resourceLocator</code>.
*
* @param resourceLocator
*/
    public void registerResourceLocator(IResourceLocator resourceLocator);

    /**
* Unregister <code>resourceLocator</code>.
*
* @param resourceLocator
*/
    public void unregisterResourceLocator(IResourceLocator resourceLocator);
}

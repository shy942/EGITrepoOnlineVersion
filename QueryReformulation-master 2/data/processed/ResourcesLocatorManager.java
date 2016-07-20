/***/
package org.eclipse.e4.ui.css.core.util.impl.resources;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.e4.ui.css.core.util.resources.IResourceLocator;
import org.eclipse.e4.ui.css.core.util.resources.IResourcesLocatorManager;
import org.eclipse.e4.ui.css.core.utils.StringUtils;

/**
* Resources locator manager implementation.
*/
public class ResourcesLocatorManager implements IResourcesLocatorManager {

    /**
* ResourcesLocatorManager Singleton
*/
    public static final IResourcesLocatorManager INSTANCE = new ResourcesLocatorManager();

    /**
* List of IResourceLocator instance which was registered.
*/
    private List<IResourceLocator> uriResolvers;

    public  ResourcesLocatorManager() {
        registerResourceLocator(new HttpResourcesLocatorImpl());
    }

    @Override
    public void registerResourceLocator(IResourceLocator resourceLocator) {
        if (uriResolvers == null) {
            uriResolvers = new ArrayList();
        }
        if (resourceLocator instanceof OSGiResourceLocator) {
            uriResolvers.add(0, resourceLocator);
        } else {
            uriResolvers.add(resourceLocator);
        }
    }

    @Override
    public void unregisterResourceLocator(IResourceLocator resourceLocator) {
        if (uriResolvers == null) {
            return;
        }
        uriResolvers.remove(resourceLocator);
    }

    @Override
    public String resolve(String uri) {
        if (StringUtils.isEmpty(uri)) {
            return null;
        }
        if (uriResolvers == null) {
            return null;
        }
        // null.
        for (IResourceLocator resolver : uriResolvers) {
            String s = resolver.resolve(uri);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public InputStream getInputStream(String uri) throws Exception {
        if (StringUtils.isEmpty(uri)) {
            return null;
        }
        if (uriResolvers == null) {
            return null;
        }
        // null.
        for (IResourceLocator resolver : uriResolvers) {
            String s = resolver.resolve(uri);
            if (s != null) {
                InputStream inputStream = resolver.getInputStream(uri);
                if (inputStream != null) {
                    return inputStream;
                }
            }
        }
        return null;
    }

    @Override
    public Reader getReader(String uri) throws Exception {
        if (StringUtils.isEmpty(uri)) {
            return null;
        }
        if (uriResolvers == null) {
            return null;
        }
        // null.
        for (IResourceLocator resolver : uriResolvers) {
            String s = resolver.resolve(uri);
            if (s != null) {
                Reader reader = resolver.getReader(uri);
                if (reader != null) {
                    return reader;
                }
            }
        }
        return null;
    }
}

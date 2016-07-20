/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IContributorResourceAdapter;
import org.eclipse.ui.ide.IContributorResourceAdapter2;

/**
* The DefaultContributorResourceAdapter is the default
* implementation of the IContributorResourceAdapter used for
* one to one resource adaption.
*/
public class DefaultContributorResourceAdapter implements IContributorResourceAdapter2 {

    private static IContributorResourceAdapter singleton;

    /**
* Constructor for DefaultContributorResourceAdapter.
*/
    public  DefaultContributorResourceAdapter() {
        super();
    }

    /**
* Return the default instance used for TaskList adapting.
* @return the default instance used for TaskList adapting
*/
    public static IContributorResourceAdapter getDefault() {
        if (singleton == null) {
            singleton = new DefaultContributorResourceAdapter();
        }
        return singleton;
    }

    /*
* @see IContributorResourceAdapter#getAdaptedResource(IAdaptable)
*/
    @Override
    public IResource getAdaptedResource(IAdaptable adaptable) {
        return Adapters.adapt(adaptable, IResource.class);
    }

    @Override
    public ResourceMapping getAdaptedResourceMapping(IAdaptable adaptable) {
        return Adapters.adapt(adaptable, ResourceMapping.class);
    }
}

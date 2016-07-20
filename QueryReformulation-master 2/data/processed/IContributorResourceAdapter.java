/***/
package org.eclipse.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

/**
* The IContributorResourceAdapter is an interface that defines
* the API required to get a resource that an object adapts to
* for use of object contributions, decorators and property
* pages that have adaptable = true.
* Implementors of this interface are typically registered with an
* IAdapterFactory for lookup via the getAdapter() mechanism.
*/
public interface IContributorResourceAdapter {

    /**
* Return the resource that the supplied adaptable
* adapts to. An IContributorResourceAdapter assumes
* that any object passed to it adapts to one equivalent
* resource.
*
* @param adaptable the adaptable being queried
* @return a resource, or <code>null</code> if there
* 	is no adapted resource for this type
*/
    public IResource getAdaptedResource(IAdaptable adaptable);
}

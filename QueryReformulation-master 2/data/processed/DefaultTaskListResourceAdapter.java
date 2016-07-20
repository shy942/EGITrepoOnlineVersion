/***/
package org.eclipse.ui.views.tasklist;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;

/**
* The DefaultTaskListResourceAdapter is the default
* implementation of the ITaskListResourceAdapter used by the
* TaskList for resource adaption.
*/
class DefaultTaskListResourceAdapter implements ITaskListResourceAdapter {

    private static ITaskListResourceAdapter singleton;

    /**
* Constructor for DefaultTaskListResourceAdapter.
*/
     DefaultTaskListResourceAdapter() {
        super();
    }

    /**
* Return the default instance used for TaskList adapting.
*/
    static ITaskListResourceAdapter getDefault() {
        if (singleton == null) {
            singleton = new DefaultTaskListResourceAdapter();
        }
        return singleton;
    }

    /*
* @see ITaskListResourceAdapter#getAffectedResource(IAdaptable)
*/
    @Override
    public IResource getAffectedResource(IAdaptable adaptable) {
        IResource resource = Adapters.adapt(adaptable, IResource.class);
        if (resource == null) {
            return Adapters.adapt(resource, IFile.class);
        }
        return resource;
    }
}

/***/
package org.eclipse.ui.internal.ide.model;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

/**
* The ResourceFactory is used to save and recreate an IResource object.
* As such, it implements the IPersistableElement interface for storage
* and the IElementFactory interface for recreation.
*
* @see IMemento
* @see IPersistableElement
* @see IElementFactory
*/
public class WorkspaceFactory implements IElementFactory, IPersistableElement {

    //$NON-NLS-1$
    private static final String FACTORY_ID = "org.eclipse.ui.internal.model.WorkspaceFactory";

    /**
* Create a ResourceFactory.  This constructor is typically used
* for our IElementFactory side.
*/
    public  WorkspaceFactory() {
    }

    /**
* @see IElementFactory
*/
    @Override
    public IAdaptable createElement(IMemento memento) {
        return ResourcesPlugin.getWorkspace();
    }

    /**
* @see IPersistableElement
*/
    @Override
    public String getFactoryId() {
        return FACTORY_ID;
    }

    /**
* @see IPersistableElement
*/
    @Override
    public void saveState(IMemento memento) {
    }
}

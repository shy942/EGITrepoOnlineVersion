/***/
package org.eclipse.jface.viewers;

import java.util.EventObject;
import org.eclipse.core.runtime.Assert;

/**
* Event object describing a selection change. The source of these
* events is a selection provider.
*
* @see ISelection
* @see ISelectionProvider
* @see ISelectionChangedListener
*/
public class SelectionChangedEvent extends EventObject {

    /**
* Generated serial version UID for this class.
* @since 3.1
*/
    private static final long serialVersionUID = 3835149545519723574L;

    /**
* The selection.
*/
    protected ISelection selection;

    /**
* Creates a new event for the given source and selection.
*
* @param source the selection provider
* @param selection the selection
*/
    public  SelectionChangedEvent(ISelectionProvider source, ISelection selection) {
        super(source);
        Assert.isNotNull(selection);
        this.selection = selection;
    }

    /**
* Returns the selection.
*
* @return the selection
*/
    public ISelection getSelection() {
        return selection;
    }

    /**
* Returns the selection provider that is the source of this event.
*
* @return the originating selection provider
*/
    public ISelectionProvider getSelectionProvider() {
        return (ISelectionProvider) getSource();
    }
}

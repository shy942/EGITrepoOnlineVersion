/***/
package org.eclipse.jface.viewers;

import java.util.EventObject;

/**
* Event object describing a tree node being expanded
* or collapsed. The source of these events is the tree viewer.
*
* @see ITreeViewerListener
*/
public class TreeExpansionEvent extends EventObject {

    /**
* Generated serial version UID for this class.
* @since 3.1
*/
    private static final long serialVersionUID = 3618414930227835185L;

    /**
* The element that was expanded or collapsed.
*/
    private Object element;

    /**
* Creates a new event for the given source and element.
*
* @param source the tree viewer
* @param element the element
*/
    public  TreeExpansionEvent(AbstractTreeViewer source, Object element) {
        super(source);
        this.element = element;
    }

    /**
* Returns the element that got expanded or collapsed.
*
* @return the element
*/
    public Object getElement() {
        return element;
    }

    /**
* Returns the originator of the event.
*
* @return the originating tree viewer
*/
    public AbstractTreeViewer getTreeViewer() {
        return (AbstractTreeViewer) source;
    }
}

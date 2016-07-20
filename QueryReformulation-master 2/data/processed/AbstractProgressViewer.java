/***/
package org.eclipse.ui.internal.progress;

import org.eclipse.jface.viewers.StructuredViewer;

/**
* The AbstractProgressViewer is the abstract superclass of the viewers that
* show progress.
*
*/
public abstract class AbstractProgressViewer extends StructuredViewer {

    /**
* Add the elements to the receiver.
* @param elements
*/
    public abstract void add(Object[] elements);

    /**
* Remove the elements from the receiver.
* @param elements
*/
    public abstract void remove(Object[] elements);
}

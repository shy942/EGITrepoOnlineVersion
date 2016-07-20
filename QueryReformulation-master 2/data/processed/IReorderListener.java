/***/
package org.eclipse.ui.internal;

/**
* Simple interface for informing clients of reordering of an object in an ordered list.
*
*/
interface IReorderListener {

    /**
* An object has been moved, clients might need to react.
*
* @param obj
* @param newIndex
*
*/
    public void reorder(Object obj, int newIndex);
}

/***/
package org.eclipse.ui.internal.themes;

/**
* Theme elements which may potentially be editted by the user should implement
* this interface.
*
* @since 3.0
*/
public interface IEditable {

    /**
* Returns whether this object is editable.
*
* @return whether this object is editable.
*/
    public boolean isEditable();
}

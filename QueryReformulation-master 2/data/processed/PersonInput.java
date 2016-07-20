/***/
package org.eclipse.ui.examples.contributions.model;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
* The editor input for looking up a person.
*
* @since 3.3
*/
public class PersonInput implements IEditorInput {

    private int index;

    public  PersonInput(int i) {
        index = i;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        //$NON-NLS-1$
        return "" + index;
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return getName();
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return null;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PersonInput) {
            return index == ((PersonInput) o).index;
        }
        return false;
    }
}

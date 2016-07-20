/***/
package org.eclipse.jface.examples.databinding.model;

import org.eclipse.jface.examples.databinding.ModelObject;

/**
* @since 3.2
*
*/
public class SimpleCart extends ModelObject {

    private int numItems;

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        firePropertyChange("numItems", this.numItems, this.numItems = numItems);
    }
}

/***/
package org.eclipse.ui.internal.keys.model;

/**
* @since 3.4
*
*/
public class CommonModel extends ModelElement {

    //$NON-NLS-1$
    public static final String PROP_SELECTED_ELEMENT = "selectedElement";

    private ModelElement selectedElement;

    /**
* @param kc
*/
    public  CommonModel(KeyController kc) {
        super(kc);
    }

    /**
* @return Returns the selectedContext.
*/
    public ModelElement getSelectedElement() {
        return selectedElement;
    }

    /**
* @param selectedContext
*            The selectedContext to set.
*/
    public void setSelectedElement(ModelElement selectedContext) {
        ModelElement old = this.selectedElement;
        this.selectedElement = selectedContext;
        controller.firePropertyChange(this, PROP_SELECTED_ELEMENT, old, selectedContext);
    }
}

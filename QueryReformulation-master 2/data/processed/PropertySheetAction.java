/***/
package org.eclipse.ui.views.properties;

import org.eclipse.jface.action.Action;

/*package*/
abstract class PropertySheetAction extends Action {

    protected PropertySheetViewer viewer;

    private String id;

    /**
* Create a PropertySheetViewer action.
*/
    protected  PropertySheetAction(PropertySheetViewer viewer, String name) {
        super(name);
        this.id = name;
        this.viewer = viewer;
    }

    /**
* Return the unique action ID that will be
* used in contribution managers.
*/
    @Override
    public String getId() {
        return id;
    }

    /**
* Return the PropertySheetViewer
* @return the PropertySheetViewer
*/
    public PropertySheetViewer getPropertySheet() {
        return viewer;
    }

    /**
* Set the unique ID that should be used
* in the contribution managers.
*/
    @Override
    public void setId(String newId) {
        id = newId;
    }
}

/***/
package org.eclipse.ui.views.properties;

import org.eclipse.ui.PlatformUI;

/*package*/
class DefaultsAction extends PropertySheetAction {

    /**
* Create the Defaults action. This action is used to set
* the properties back to their default values.
*
* @param viewer the viewer
* @param name the name
*/
    public  DefaultsAction(PropertySheetViewer viewer, String name) {
        super(viewer, name);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IPropertiesHelpContextIds.DEFAULTS_ACTION);
    }

    /**
* Reset the properties to their default values.
*/
    @Override
    public void run() {
        getPropertySheet().deactivateCellEditor();
        getPropertySheet().resetProperties();
    }
}

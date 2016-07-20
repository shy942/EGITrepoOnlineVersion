/***/
package org.eclipse.ui.views.properties;

import org.eclipse.ui.PlatformUI;

/*package*/
class FilterAction extends PropertySheetAction {

    /**
* Create the Filter action. This action is used to show
* or hide expert properties.
*
* @param viewer the viewer
* @param name the name
*/
    public  FilterAction(PropertySheetViewer viewer, String name) {
        super(viewer, name);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IPropertiesHelpContextIds.FILTER_ACTION);
    }

    /**
* Toggle the display of expert properties.
*/
    @Override
    public void run() {
        PropertySheetViewer ps = getPropertySheet();
        ps.deactivateCellEditor();
        if (isChecked()) {
            ps.showExpert();
        } else {
            ps.hideExpert();
        }
    }
}

/***/
package org.eclipse.e4.ui.workbench.addons.swt;

import java.util.List;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
* Model processors which adds the DnD add-on to the application model
*/
public class DnDProcessor {

    @Execute
    void addDnDAddon(MApplication app, EModelService modelService) {
        List<MAddon> addons = app.getAddons();
        // prevent multiple copies
        for (MAddon addon : addons) {
            if (//$NON-NLS-1$
            addon.getContributionURI().contains("ui.workbench.addons.dndaddon.DnDAddon"))
                return;
        }
        // adds the add-on to the application model
        MAddon dndAddon = modelService.createModelElement(MAddon.class);
        //$NON-NLS-1$
        dndAddon.setElementId("DnDAddon");
        //$NON-NLS-1$
        dndAddon.setContributionURI("bundleclass://org.eclipse.e4.ui.workbench.addons.swt/org.eclipse.e4.ui.workbench.addons.dndaddon.DnDAddon");
        app.getAddons().add(dndAddon);
    }
}

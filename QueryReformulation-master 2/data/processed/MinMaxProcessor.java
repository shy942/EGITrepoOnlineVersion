/***/
package org.eclipse.e4.ui.workbench.addons.swt;

import java.util.List;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
* Model processors which adds the MinMax add-on to the application model
*/
public class MinMaxProcessor {

    @Execute
    void addMinMaxAddon(MApplication app, EModelService modelService) {
        List<MAddon> addons = app.getAddons();
        // prevent multiple copies
        for (MAddon addon : addons) {
            if (//$NON-NLS-1$
            addon.getContributionURI().contains("ui.workbench.addons.minmax.MinMaxAddon"))
                return;
        }
        // add the add-on to the application model
        MAddon minMaxAddon = modelService.createModelElement(MAddon.class);
        //$NON-NLS-1$
        minMaxAddon.setElementId("MinMaxAddon");
        minMaxAddon.setContributionURI(//$NON-NLS-1$
        "bundleclass://org.eclipse.e4.ui.workbench.addons.swt/org.eclipse.e4.ui.workbench.addons.minmax.MinMaxAddon");
        app.getAddons().add(minMaxAddon);
    }
}

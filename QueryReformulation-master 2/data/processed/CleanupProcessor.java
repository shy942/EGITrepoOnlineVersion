/***/
package org.eclipse.e4.ui.workbench.addons.swt;

import java.util.List;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
* Model processors which adds the cleanup add-on to the application model
*/
public class CleanupProcessor {

    @Execute
    void addCleanupAddon(MApplication app, EModelService modelService) {
        List<MAddon> addons = app.getAddons();
        // prevent multiple copies
        for (MAddon addon : addons) {
            if (addon.getContributionURI().contains(//$NON-NLS-1$
            "ui.workbench.addons.cleanupaddon.CleanupAddon"))
                return;
        }
        // adds the add-on to the application model
        MAddon cleanupAddon = modelService.createModelElement(MAddon.class);
        //$NON-NLS-1$
        cleanupAddon.setElementId("CleanupAddon");
        cleanupAddon.setContributionURI(//$NON-NLS-1$
        "bundleclass://org.eclipse.e4.ui.workbench.addons.swt/org.eclipse.e4.ui.workbench.addons.cleanupaddon.CleanupAddon");
        app.getAddons().add(cleanupAddon);
    }
}

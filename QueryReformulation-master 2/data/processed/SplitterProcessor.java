/***/
package org.eclipse.e4.ui.workbench.addons.swt;

import java.util.List;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
* Model processors which adds the Splitter add-on to the application model
*/
public class SplitterProcessor {

    @Execute
    void addSplitterAddon(MApplication app, EModelService modelService) {
        List<MAddon> addons = app.getAddons();
        // prevent multiple copies
        for (MAddon addon : addons) {
            if (addon.getContributionURI().contains(//$NON-NLS-1$
            "ui.workbench.addons.splitteraddon.SplitterAddon"))
                return;
        }
        // adds the add-on to the application model
        MAddon splitterAddon = modelService.createModelElement(MAddon.class);
        //$NON-NLS-1$
        splitterAddon.setElementId("SplitterAddon");
        splitterAddon.setContributionURI(//$NON-NLS-1$
        "bundleclass://org.eclipse.e4.ui.workbench.addons.swt/org.eclipse.e4.ui.workbench.addons.splitteraddon.SplitterAddon");
        app.getAddons().add(splitterAddon);
    }
}

/***/
package org.eclipse.ui.internal.menus;

import java.util.ArrayList;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.ContributionsAnalyzer;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuContribution;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBarContribution;
import org.eclipse.e4.ui.model.application.ui.menu.MTrimContribution;
import org.eclipse.e4.ui.model.application.ui.menu.impl.MenuFactoryImpl;
import org.eclipse.e4.ui.workbench.renderers.swt.ContributionRecord;
import org.eclipse.e4.ui.workbench.renderers.swt.ToolBarContributionRecord;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
* @since 3.102.0
*
*/
public class MenuFactoryGenerator {

    private MApplication application;

    private IConfigurationElement configElement;

    private MenuLocationURI location;

    public  MenuFactoryGenerator(MApplication application, IEclipseContext appContext, IConfigurationElement configElement, String attribute) {
        this.application = application;
        // this.appContext = appContext;
        assert appContext.equals(this.application.getContext());
        this.configElement = configElement;
        this.location = new MenuLocationURI(attribute);
    }

    private boolean inToolbar() {
        //$NON-NLS-1$
        return location.getScheme().startsWith("toolbar");
    }

    public void mergeIntoModel(ArrayList<MMenuContribution> menuContributions, ArrayList<MToolBarContribution> toolBarContributions, ArrayList<MTrimContribution> trimContributions) {
        if (location.getPath() == null || location.getPath().length() == 0) {
            WorkbenchPlugin.log(//$NON-NLS-1$
            "MenuFactoryGenerator.mergeIntoModel: Invalid menu URI: " + location);
            return;
        }
        if (inToolbar()) {
            if (MenuAdditionCacheEntry.isInWorkbenchTrim(location)) {
            // processTrimChildren(trimContributions, toolBarContributions,
            // configElement);
            } else {
                String query = location.getQuery();
                if (query == null || query.length() == 0) {
                    //$NON-NLS-1$
                    query = "after=additions";
                }
                processToolbarChildren(toolBarContributions, configElement, location.getPath(), query);
            }
            return;
        }
        MMenuContribution menuContribution = MenuFactoryImpl.eINSTANCE.createMenuContribution();
        String idContrib = MenuHelper.getId(configElement);
        if (idContrib != null && idContrib.length() > 0) {
            menuContribution.setElementId(idContrib);
        }
        if ("org.eclipse.ui.popup.any".equals(location.getPath())) {
            //$NON-NLS-1$
            //$NON-NLS-1$
            menuContribution.setParentId("popup");
        } else {
            menuContribution.setParentId(location.getPath());
        }
        String query = location.getQuery();
        if (query == null || query.length() == 0) {
            //$NON-NLS-1$
            query = "after=additions";
        }
        menuContribution.setPositionInParent(query);
        //$NON-NLS-1$
        menuContribution.getTags().add("scheme:" + location.getScheme());
        String filter = ContributionsAnalyzer.MC_MENU;
        if ("popup".equals(location.getScheme())) {
            //$NON-NLS-1$
            filter = ContributionsAnalyzer.MC_POPUP;
        }
        menuContribution.getTags().add(filter);
        menuContribution.setVisibleWhen(MenuHelper.getVisibleWhen(configElement));
        ContextFunction generator = new ContributionFactoryGenerator(configElement, 0);
        menuContribution.getTransientData().put(ContributionRecord.FACTORY, generator);
        menuContributions.add(menuContribution);
    }

    private void processToolbarChildren(ArrayList<MToolBarContribution> contributions, IConfigurationElement toolbar, String parentId, String position) {
        MToolBarContribution toolBarContribution = MenuFactoryImpl.eINSTANCE.createToolBarContribution();
        String idContrib = MenuHelper.getId(toolbar);
        if (idContrib != null && idContrib.length() > 0) {
            toolBarContribution.setElementId(idContrib);
        }
        toolBarContribution.setParentId(parentId);
        toolBarContribution.setPositionInParent(position);
        //$NON-NLS-1$
        toolBarContribution.getTags().add("scheme:" + location.getScheme());
        ContextFunction generator = new ContributionFactoryGenerator(configElement, 1);
        toolBarContribution.getTransientData().put(ToolBarContributionRecord.FACTORY, generator);
        contributions.add(toolBarContribution);
    }
}

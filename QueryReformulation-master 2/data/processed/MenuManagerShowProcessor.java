/***/
package org.eclipse.e4.ui.workbench.renderers.swt;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.contributions.IContributionFactory;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.internal.workbench.swt.AbstractPartRenderer;
import org.eclipse.e4.ui.internal.workbench.swt.Policy;
import org.eclipse.e4.ui.internal.workbench.swt.WorkbenchSWTActivator;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.menu.MDynamicMenuContribution;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MPopupMenu;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.swt.factories.IRendererFactory;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Widget;

/**
* <code>MenuManagerShowProcessor</code> provides hooks for renderer processing
* before and after the <code>MenuManager</code> calls out to its
* <code>IMenuManagerListener2</code> for the <code>menuAboutToShow</code>
* events.
*/
public class MenuManagerShowProcessor implements IMenuListener2 {

    private static void trace(String msg, Widget menu, MMenu menuModel) {
        WorkbenchSWTActivator.trace(Policy.MENUS, //$NON-NLS-1$ //$NON-NLS-2$
        msg + ": " + menu + ": " + menuModel, null);
    }

    @Inject
    private EModelService modelService;

    @Inject
    private IRendererFactory rendererFactory;

    @Inject
    private MenuManagerRenderer renderer;

    @Inject
    private IContributionFactory contributionFactory;

    @Inject
    @Optional
    private Logger logger;

    @Override
    public void menuAboutToShow(IMenuManager manager) {
        if (!(manager instanceof MenuManager)) {
            return;
        }
        MenuManager menuManager = (MenuManager) manager;
        final MMenu menuModel = renderer.getMenuModel(menuManager);
        final Menu menu = menuManager.getMenu();
        if (menuModel != null && menuManager != null) {
            cleanUp(menuModel, menuManager);
            if (menuManager.getRemoveAllWhenShown()) {
                // This needs to be done or else menu items get added multiple
                // times to MenuModel which results in incorrect behavior and
                // memory leak - bug 486474
                menuModel.getChildren().removeAll(menuModel.getChildren());
            }
        }
        if (menuModel instanceof MPopupMenu) {
            showPopup(menu, (MPopupMenu) menuModel, menuManager);
        }
        AbstractPartRenderer obj = rendererFactory.getRenderer(menuModel, menu.getParent());
        if (!(obj instanceof MenuManagerRenderer)) {
            //$NON-NLS-1$
            trace("Not the correct renderer: " + obj, menu, menuModel);
            return;
        }
        MenuManagerRenderer renderer = (MenuManagerRenderer) obj;
        if (menuModel.getWidget() == null) {
            renderer.bindWidget(menuModel, menuManager.getMenu());
        }
    }

    @Override
    public void menuAboutToHide(IMenuManager manager) {
        if (!(manager instanceof MenuManager)) {
            return;
        }
        MenuManager menuManager = (MenuManager) manager;
        final MMenu menuModel = renderer.getMenuModel(menuManager);
        final Menu menu = menuManager.getMenu();
        if (menuModel != null) {
            processDynamicElements(menuModel, menuManager);
            showMenu(menu, menuModel, menuManager);
        }
    }

    /**
* HashMap key for storage of {@link MDynamicMenuContribution} elements used
* in {@link #processDynamicElements(MMenu, MenuManager)}
*/
    protected static final String DYNAMIC_ELEMENT_STORAGE_KEY = MenuManagerShowProcessor.class.getSimpleName() + //$NON-NLS-1$
    ".dynamicElements";

    /**
* Process dynamic menu contributions provided by
* {@link MDynamicMenuContribution} application model elements
*
* @param menuModel
* @param menuManager
*
*/
    private void processDynamicElements(MMenu menuModel, MenuManager menuManager) {
        MMenuElement[] ml = menuModel.getChildren().toArray(new MMenuElement[menuModel.getChildren().size()]);
        for (int i = 0; i < ml.length; i++) {
            MMenuElement currentMenuElement = ml[i];
            if (currentMenuElement instanceof MDynamicMenuContribution) {
                Object contribution = ((MDynamicMenuContribution) currentMenuElement).getObject();
                if (contribution == null) {
                    IEclipseContext context = modelService.getContainingContext(menuModel);
                    contribution = contributionFactory.create(((MDynamicMenuContribution) currentMenuElement).getContributionURI(), context);
                    ((MDynamicMenuContribution) currentMenuElement).setObject(contribution);
                }
                IEclipseContext dynamicMenuContext = EclipseContextFactory.create();
                ArrayList<MMenuElement> mel = new ArrayList();
                dynamicMenuContext.set(List.class, mel);
                IEclipseContext parentContext = modelService.getContainingContext(currentMenuElement);
                Object rc = ContextInjectionFactory.invoke(contribution, AboutToShow.class, parentContext, dynamicMenuContext, this);
                dynamicMenuContext.dispose();
                if (rc == this) {
                    if (logger != null) {
                        //$NON-NLS-1$
                        logger.error("Missing @AboutToShow method in " + contribution);
                    }
                    continue;
                }
                if (mel.size() > 0) {
                    int position = 0;
                    while (position < menuModel.getChildren().size()) {
                        if (currentMenuElement == menuModel.getChildren().get(position)) {
                            position++;
                            break;
                        }
                        position++;
                    }
                    // and set the parent of the entries
                    for (int j = 0; j < mel.size(); j++) {
                        MMenuElement menuElement = mel.get(j);
                        if (menuElement.getElementId() == null || menuElement.getElementId().length() < 1) {
                            menuElement.setElementId(currentMenuElement.getElementId() + "." + //$NON-NLS-1$
                            j);
                        }
                        menuModel.getChildren().add(position++, menuElement);
                        renderer.modelProcessSwitch(menuManager, menuElement);
                    }
                    currentMenuElement.getTransientData().put(DYNAMIC_ELEMENT_STORAGE_KEY, mel);
                }
            }
        }
    }

    /**
* Remove all of the items created by any dynamic contributions on the
* menuModel.
*
* @param menuModel
* @param menuManager
*/
    private void cleanUp(MMenu menuModel, MenuManager menuManager) {
        renderer.removeDynamicMenuContributions(menuManager, menuModel);
    }

    private void showPopup(final Menu menu, final MPopupMenu menuModel, MenuManager menuManager) {
        // System.err.println("showPopup: " + menuModel + "\n\t" + menu);
        // we need some context foolery here
        final IEclipseContext popupContext = menuModel.getContext();
        final IEclipseContext parentContext = popupContext.getParent();
        final IEclipseContext originalChild = parentContext.getActiveChild();
        popupContext.activate();
        popupContext.set(MenuManagerRendererFilter.TMP_ORIGINAL_CONTEXT, originalChild);
    }

    private void showMenu(final Menu menu, final MMenu menuModel, MenuManager menuManager) {
        final IEclipseContext evalContext;
        if (menuModel instanceof MContext) {
            evalContext = ((MContext) menuModel).getContext();
        } else {
            evalContext = modelService.getContainingContext(menuModel);
        }
        MenuManagerRendererFilter.updateElementVisibility(menuModel, renderer, menuManager, evalContext, 2, true);
    }
}

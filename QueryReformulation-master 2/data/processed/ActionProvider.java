/***/
package org.eclipse.ui.internal.quickaccess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
* @since 3.3
*
*/
public class ActionProvider extends QuickAccessProvider {

    private Map idToElement;

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "org.eclipse.ui.actions";
    }

    @Override
    public QuickAccessElement getElementForId(String id) {
        getElements();
        return (ActionElement) idToElement.get(id);
    }

    @Override
    public QuickAccessElement[] getElements() {
        if (idToElement == null) {
            idToElement = new HashMap();
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window instanceof WorkbenchWindow) {
                MenuManager menu = ((WorkbenchWindow) window).getMenuManager();
                Set result = new HashSet();
                collectContributions(menu, result);
                ActionContributionItem[] actions = (ActionContributionItem[]) result.toArray(new ActionContributionItem[result.size()]);
                for (int i = 0; i < actions.length; i++) {
                    ActionElement actionElement = new ActionElement(actions[i], this);
                    idToElement.put(actionElement.getId(), actionElement);
                }
            }
        }
        return (ActionElement[]) idToElement.values().toArray(new ActionElement[idToElement.values().size()]);
    }

    private void collectContributions(MenuManager menu, Set result) {
        IContributionItem[] items = menu.getItems();
        for (int i = 0; i < items.length; i++) {
            IContributionItem item = items[i];
            if (item instanceof SubContributionItem) {
                item = ((SubContributionItem) item).getInnerItem();
            }
            if (item instanceof MenuManager) {
                collectContributions((MenuManager) item, result);
            } else if (item instanceof ActionContributionItem && item.isEnabled()) {
                result.add(item);
            }
        }
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_OBJ_NODE);
    }

    @Override
    public String getName() {
        return QuickAccessMessages.QuickAccess_Menus;
    }

    @Override
    protected void doReset() {
        idToElement = null;
    }
}

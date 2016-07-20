/***/
package org.eclipse.ui.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* This manager is used to populate a popup menu manager with actions
* for a given type.
*/
public class ObjectActionContributorManager extends ObjectContributorManager {

    private static ObjectActionContributorManager sharedInstance;

    /**
* PopupMenuManager constructor.
*/
    public  ObjectActionContributorManager() {
        super();
        loadContributors();
    }

    /**
* Contributes submenus and/or actions applicable to the selection in the
* provided viewer into the provided popup menu.
*
* @param part
*            the part being contributed to
* @param popupMenu
*            the menu being contributed to
* @param selProv
*            the selection provider
* @param alreadyContributed
*            the set of contributors that already contributed to the menu
* @return whether anything was contributed
*/
    public boolean contributeObjectActions(IWorkbenchPart part, IMenuManager popupMenu, ISelectionProvider selProv, Set<IObjectActionContributor> alreadyContributed) {
        // Get a selection.
        ISelection selection = selProv.getSelection();
        if (selection == null) {
            return false;
        }
        // Convert the selection into an element vector.
        // According to the dictionary, a selection is "one that
        // is selected", or "a collection of selected things".
        // In reflection of this, we deal with one or a collection.
        List elements = null;
        if (selection instanceof IStructuredSelection) {
            elements = ((IStructuredSelection) selection).toList();
        } else {
            elements = new ArrayList(1);
            elements.add(selection);
        }
        List<IObjectActionContributor> contributors = getContributors(elements);
        contributors.removeAll(alreadyContributed);
        if (contributors.isEmpty()) {
            return false;
        }
        // First pass, add the menus and collect the overrides. Prune from the
        // list any non-applicable contributions.
        boolean actualContributions = false;
        ArrayList overrides = new ArrayList(4);
        for (Iterator<IObjectActionContributor> it = contributors.iterator(); it.hasNext(); ) {
            IObjectActionContributor contributor = it.next();
            if (!isApplicableTo(elements, contributor)) {
                it.remove();
                continue;
            }
            if (contributor.contributeObjectMenus(popupMenu, selProv)) {
                actualContributions = true;
                alreadyContributed.add(contributor);
            }
            contributor.contributeObjectActionIdOverrides(overrides);
        }
        // the selection.
        for (Iterator<IObjectActionContributor> it = contributors.iterator(); it.hasNext(); ) {
            IObjectActionContributor contributor = it.next();
            if (contributor.contributeObjectActions(part, popupMenu, selProv, overrides)) {
                actualContributions = true;
                alreadyContributed.add(contributor);
            }
        }
        return actualContributions;
    }

    /**
* Returns the shared instance of this manager.
* @return the shared instance of this manager
*/
    public static ObjectActionContributorManager getManager() {
        if (sharedInstance == null) {
            sharedInstance = new ObjectActionContributorManager();
        }
        return sharedInstance;
    }

    /**
* Loads the contributors from the workbench's registry.
*/
    private void loadContributors() {
        ObjectActionContributorReader reader = new ObjectActionContributorReader();
        reader.readPopupContributors(this);
    }

    @Override
    public void addExtension(IExtensionTracker tracker, IExtension addedExtension) {
        IConfigurationElement[] addedElements = addedExtension.getConfigurationElements();
        for (int i = 0; i < addedElements.length; i++) {
            ObjectActionContributorReader reader = new ObjectActionContributorReader();
            reader.setManager(this);
            reader.readElement(addedElements[i]);
        }
    }

    @Override
    protected String getExtensionPointFilter() {
        return IWorkbenchRegistryConstants.PL_POPUP_MENU;
    }
}

/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchPart;

/**
* This class adds to the PluginAction support by
* setting itself up for work within a WorkbenchPart.
* The main difference is that it is capable of
* processing local selection changes within a part.
*/
public class PartPluginAction extends PluginAction {

    /**
* PartPluginAction constructor.
*/
    public  PartPluginAction(IConfigurationElement actionElement, String id, int style) {
        super(actionElement, id, style);
    }

    /**
* Registers this action as a listener of the workbench part.
*/
    protected void registerSelectionListener(IWorkbenchPart aPart) {
        ISelectionProvider selectionProvider = aPart.getSite().getSelectionProvider();
        if (selectionProvider != null) {
            selectionProvider.addSelectionChangedListener(this);
            selectionChanged(selectionProvider.getSelection());
        }
    }

    /**
* Unregisters this action as a listener of the workbench part.
*/
    protected void unregisterSelectionListener(IWorkbenchPart aPart) {
        ISelectionProvider selectionProvider = aPart.getSite().getSelectionProvider();
        if (selectionProvider != null) {
            selectionProvider.removeSelectionChangedListener(this);
        }
    }
}

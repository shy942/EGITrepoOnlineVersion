/***/
package org.eclipse.ui.internal.navigator.dnd;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.internal.navigator.Policy;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.part.IDropActionDelegate;
import org.eclipse.ui.part.PluginTransferData;

/**
*
*
* @since 3.2
*
*/
public class NavigatorPluginDropAction implements IDropActionDelegate {

    //$NON-NLS-1$
    private static final String CN_PLUGIN_ACTION_ID = "org.eclipse.ui.navigator.PluginDropAction";

    /**
* A default no-args constructor is required by the
* <b>org.eclipse.ui.dropAdapters</b> extension point
*/
    public  NavigatorPluginDropAction() {
        super();
    }

    @Override
    public boolean run(Object sourceData, Object target) {
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("NavigatorPluginDropAction.run (begin)");
        }
        String sourceViewerId = new String((byte[]) sourceData);
        IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer.getTransfer().getSelection();
        INavigatorContentService contentService = NavigatorContentServiceTransfer.getInstance().findService(sourceViewerId);
        if (contentService == null) {
            return false;
        }
        try {
            CommonDropAdapterAssistant[] assistants = contentService.getDnDService().findCommonDropAdapterAssistants(target, selection);
            IStatus valid = null;
            for (int i = 0; i < assistants.length; i++) {
                valid = assistants[i].validatePluginTransferDrop(selection, target);
                if (valid != null && valid.isOK()) {
                    valid = assistants[i].handlePluginTransferDrop(selection, target);
                    return valid != null && valid.isOK();
                }
            }
        } finally {
            NavigatorContentServiceTransfer.getInstance().unregisterContentService(contentService);
        }
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("NavigatorPluginDropAction.run (exit)");
        }
        return false;
    }

    /**
*
* @param aContentService
*            The associated content service that is the source of the drag
* @return A PluginTransferData properly configured to call the Common
*         Navigator's PluginDropAction.
*/
    public static PluginTransferData createTransferData(INavigatorContentService aContentService) {
        NavigatorContentServiceTransfer.getInstance().registerContentService(aContentService);
        return new PluginTransferData(CN_PLUGIN_ACTION_ID, aContentService.getViewerId().getBytes());
    }
}

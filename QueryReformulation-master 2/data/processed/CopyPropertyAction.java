/***/
package org.eclipse.ui.views.properties;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.properties.PropertiesMessages;

/*package*/
class CopyPropertyAction extends PropertySheetAction {

    /**
* System clipboard
*/
    private Clipboard clipboard;

    /**
* Creates the action.
*
* @param viewer the viewer
* @param name the name
* @param clipboard the clipboard
*/
    public  CopyPropertyAction(PropertySheetViewer viewer, String name, Clipboard clipboard) {
        super(viewer, name);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IPropertiesHelpContextIds.COPY_PROPERTY_ACTION);
        this.clipboard = clipboard;
    }

    /**
* Performs this action.
*/
    @Override
    public void run() {
        // Get the selected property
        IStructuredSelection selection = (IStructuredSelection) getPropertySheet().getSelection();
        if (selection.isEmpty()) {
            return;
        }
        // Assume single selection
        IPropertySheetEntry entry = (IPropertySheetEntry) selection.getFirstElement();
        // Place text on the clipboard
        StringBuffer buffer = new StringBuffer();
        buffer.append(entry.getDisplayName());
        //$NON-NLS-1$
        buffer.append("\t");
        buffer.append(entry.getValueAsString());
        setClipboard(buffer.toString());
    }

    /**
* Updates enablement based on the current selection.
*
* @param sel the selection
*/
    public void selectionChanged(IStructuredSelection sel) {
        setEnabled(!sel.isEmpty());
    }

    private void setClipboard(String text) {
        try {
            Object[] data = new Object[] { text };
            Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
            clipboard.setContents(data, transferTypes);
        } catch (SWTError e) {
            if (e.code != DND.ERROR_CANNOT_SET_CLIPBOARD) {
                throw e;
            }
            if (MessageDialog.openQuestion(getPropertySheet().getControl().getShell(), PropertiesMessages.CopyToClipboardProblemDialog_title, PropertiesMessages.CopyToClipboardProblemDialog_message)) {
                setClipboard(text);
            }
        }
    }
}

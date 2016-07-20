/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.EditorSelectionDialog;
import org.eclipse.ui.ide.IUnassociatedEditorStrategy;

/**
* @since 3.12
*
*/
public class AskUserViaPopupUnassociatedEditorStrategy implements IUnassociatedEditorStrategy {

    @Override
    public IEditorDescriptor getEditorDescriptor(String fileName, IEditorRegistry editorRegistry) {
        EditorSelectionDialog dialog = new EditorSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        dialog.setFileName(fileName);
        dialog.setBlockOnOpen(true);
        if (IDialogConstants.CANCEL_ID == dialog.open()) {
            throw new OperationCanceledException(IDEWorkbenchMessages.IDE_noFileEditorSelectedUserCanceled);
        }
        return dialog.getSelectedEditor();
    }
}

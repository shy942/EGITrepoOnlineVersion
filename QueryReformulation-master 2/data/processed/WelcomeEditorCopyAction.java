/***/
package org.eclipse.ui.internal.ide.dialogs;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

/**
* Global copy action for the welcome editor.
*/
public class WelcomeEditorCopyAction extends Action {

    private WelcomeEditor editorPart;

    public  WelcomeEditorCopyAction(WelcomeEditor editor) {
        editorPart = editor;
        setText(IDEWorkbenchMessages.WelcomeEditor_copy_text);
    }

    @Override
    public void run() {
        editorPart.getCurrentText().copy();
    }
}

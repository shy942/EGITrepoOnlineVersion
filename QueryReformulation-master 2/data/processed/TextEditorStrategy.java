/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.ide.IUnassociatedEditorStrategy;

/**
* @since 3.12
*
*/
public final class TextEditorStrategy implements IUnassociatedEditorStrategy {

    @Override
    public IEditorDescriptor getEditorDescriptor(String name, IEditorRegistry editorReg) {
        return editorReg.findEditor(IDEWorkbenchPlugin.DEFAULT_TEXT_EDITOR_ID);
    }
}

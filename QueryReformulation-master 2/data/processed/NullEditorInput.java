/***/
package org.eclipse.ui.internal.part;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.internal.EditorReference;

/**
* @since 3.1
*/
public class NullEditorInput implements IEditorInput {

    private EditorReference editorReference;

    /**
* Creates a <code>NullEditorInput</code>.
*/
    public  NullEditorInput() {
    }

    /**
* Creates a <code>NullEditorInput</code> for the
* given editor reference.
*
* @param editorReference the editor reference
* @since 3.4
*/
    public  NullEditorInput(EditorReference editorReference) {
        Assert.isLegal(editorReference != null);
        this.editorReference = editorReference;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return ImageDescriptor.getMissingImageDescriptor();
    }

    @Override
    public String getName() {
        String result = null;
        if (editorReference != null) {
            result = editorReference.getName();
        }
        if (result != null) {
            return result;
        }
        //$NON-NLS-1$
        return "";
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        if (editorReference != null)
            return editorReference.getTitleToolTip();
        //$NON-NLS-1$
        return "";
    }

    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }
}

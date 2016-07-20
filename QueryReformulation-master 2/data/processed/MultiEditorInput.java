/***/
package org.eclipse.ui.part;

import java.util.Arrays;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
* Implements an input for a <code>AbstractMultiEditor</code>.
*
* This class is intended to be instantiated by clients but is
* not intended to be subclassed.
* @noextend This class is not intended to be subclassed by clients.
*/
public class MultiEditorInput implements IEditorInput {

    IEditorInput input[];

    String editors[];

    /**
* Constructs a new MultiEditorInput.
*/
    public  MultiEditorInput(String[] editorIDs, IEditorInput[] innerEditors) {
        Assert.isNotNull(editorIDs);
        Assert.isNotNull(innerEditors);
        editors = editorIDs;
        input = innerEditors;
    }

    /**
* Returns an array with the input of all inner editors.
*/
    public IEditorInput[] getInput() {
        return input;
    }

    /**
* Retunrs an array with the id of all inner editors.
*/
    public String[] getEditors() {
        return editors;
    }

    /*
* @see IEditorInput#exists()
*/
    @Override
    public boolean exists() {
        return true;
    }

    /*
* @see IEditorInput#getImageDescriptor()
*/
    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    /*
* @see IEditorInput#getName()
*/
    @Override
    public String getName() {
        //$NON-NLS-1$
        String name = "";
        for (int i = 0; i < (input.length - 1); i++) {
            //$NON-NLS-1$
            name = name + input[i].getName() + "/";
        }
        name = name + input[input.length - 1].getName();
        return name;
    }

    /*
* @see IEditorInput#getPersistable()
*/
    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    /*
* @see IEditorInput#getToolTipText()
*/
    @Override
    public String getToolTipText() {
        return getName();
    }

    /*
* @see IAdaptable#getAdapter(Class)
*/
    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MultiEditorInput)) {
            return false;
        }
        MultiEditorInput other = (MultiEditorInput) obj;
        return Arrays.equals(this.editors, other.editors) && Arrays.equals(this.input, other.input);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < editors.length; i++) {
            hash = hash * 37 + editors[i].hashCode();
        }
        for (int i = 0; i < input.length; i++) {
            hash = hash * 37 + input[i].hashCode();
        }
        return hash;
    }
}
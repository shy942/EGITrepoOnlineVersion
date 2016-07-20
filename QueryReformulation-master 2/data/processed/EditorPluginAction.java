/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.WorkbenchException;

/**
* Extends PartPluginAction for usage in editor parts. Objects
* of this class are created by reading the registry (extension point "editorActions").
*/
public final class EditorPluginAction extends PartPluginAction {

    private IEditorPart currentEditor;

    /**
* This class adds the requirement that action delegates
* loaded on demand implement IViewActionDelegate
*/
    public  EditorPluginAction(IConfigurationElement actionElement, IEditorPart part, String id, int style) {
        super(actionElement, id, style);
        if (part != null) {
            editorChanged(part);
        }
    }

    @Override
    protected IActionDelegate validateDelegate(Object obj) throws WorkbenchException {
        if (obj instanceof IEditorActionDelegate) {
            return (IEditorActionDelegate) obj;
        } else {
            throw new WorkbenchException(//$NON-NLS-1$
            "Action must implement IEditorActionDelegate");
        }
    }

    @Override
    protected void initDelegate() {
        super.initDelegate();
        ((IEditorActionDelegate) getDelegate()).setActiveEditor(this, currentEditor);
    }

    /**
* Handles editor change by re-registering for selection
* changes and updating IEditorActionDelegate.
*/
    public void editorChanged(IEditorPart part) {
        if (currentEditor != null) {
            unregisterSelectionListener(currentEditor);
        }
        currentEditor = part;
        if (getDelegate() == null && isOkToCreateDelegate()) {
            createDelegate();
        }
        if (getDelegate() != null) {
            ((IEditorActionDelegate) getDelegate()).setActiveEditor(this, part);
        }
        if (part != null) {
            registerSelectionListener(part);
        }
    }

    @Override
    public void dispose() {
        if (currentEditor != null) {
            unregisterSelectionListener(currentEditor);
            currentEditor = null;
        }
        super.dispose();
    }
}

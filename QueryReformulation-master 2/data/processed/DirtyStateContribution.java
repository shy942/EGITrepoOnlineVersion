/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;

/**
* This class demonstrates the contribution of a custom control to the
* status line for the readme editor.  The control shows the
* dirty status of the editor.
*/
public class DirtyStateContribution extends ControlContribution implements IPropertyListener {

    private Composite composite;

    private Label label;

    private IEditorPart activeEditor;

    /**
* Creates a new DirtyStateContribution.
*/
    protected  DirtyStateContribution() {
        //$NON-NLS-1$
        super("DirtyState");
    }

    @Override
    protected Control createControl(Composite parent) {
        // If the composite is good just return it.
        if (composite != null && !composite.isDisposed())
            return composite;
        // Create composite for border.
        composite = new Composite(parent, SWT.BORDER);
        composite.setData(this);
        // Create label inside composite.
        label = new Label(composite, SWT.NONE);
        label.setSize(80, 15);
        updateState();
        return composite;
    }

    /**
* Called when an editor is activated.
*
* @see ReadmeEditorActionBarContributor#setActiveEditor(IEditorPart)
*/
    public void editorChanged(IEditorPart part) {
        if (activeEditor != null) {
            activeEditor.removePropertyListener(this);
        }
        activeEditor = part;
        if (activeEditor != null) {
            activeEditor.addPropertyListener(this);
        }
        updateState();
    }

    @Override
    public void propertyChanged(Object source, int propID) {
        if (source instanceof IEditorPart)
            updateState();
    }

    /**
* Updates the state of the label.
*/
    private void updateState() {
        if (label == null || label.isDisposed())
            return;
        boolean saveNeeded = false;
        if (activeEditor != null)
            saveNeeded = activeEditor.isDirty();
        if (saveNeeded)
            //$NON-NLS-1$
            label.setText(MessageUtil.getString("Save_Needed"));
        else
            //$NON-NLS-1$
            label.setText(MessageUtil.getString("Clean"));
    }
}

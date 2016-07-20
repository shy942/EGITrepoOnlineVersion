/***/
package org.eclipse.ui.tests.multieditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
* A test editor that does pretty-well nothing.  Activating it will
* update the coolbar, and possibly eventually the outline as well.
*
* @since 3.1
*
*/
public class TestEditor extends EditorPart {

    private Composite fMainPanel;

    public  TestEditor() {
        super();
    // TODO Auto-generated constructor stub
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
    // TODO Auto-generated method stub
    }

    @Override
    public void doSaveAs() {
    // TODO Auto-generated method stub
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof IFileEditorInput)) {
            throw new PartInitException("Invalid Input: Must be IFileEditorInput");
        }
        setSite(site);
        setInput(input);
    }

    @Override
    public boolean isDirty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        fMainPanel = new Composite(parent, SWT.NONE);
        fMainPanel.setLayout(new RowLayout(SWT.VERTICAL));
        Label l = new Label(fMainPanel, SWT.NONE);
        l.setText("Editor Title:");
        l = new Label(fMainPanel, SWT.BORDER);
        l.setText(getEditorInput().getName());
    }

    @Override
    public void setFocus() {
        fMainPanel.setFocus();
    }
}

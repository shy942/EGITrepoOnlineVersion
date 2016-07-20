/***/
package org.eclipse.ui.tests.multipageeditor;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.part.EditorPart;

/**
* A multi-page editor for testing key bindings while switching pages. This is
* one of two pages -- each with a different context. The first context binds
* "Ctrl+Shift+4" -- the second binds "Ctrl+Shift+5" -- to the command
* "org.eclipse.ui.tests.TestCommand". Which context to use is determined by a
* page number, which is passed as a parameter.
*
* @since 3.0
*/
public final class TestKeyBindingMultiPageEditorPart extends EditorPart {

    /**
* The page number for this part.
*
*/
    private final int number;

    /**
* Constructs a new instance of
* <code>TestKeyBindingMultiPageEditorPart</code> with the page number of
* this page.
*
* @param number
*            The page number for this part.
*/
    public  TestKeyBindingMultiPageEditorPart(int number) {
        super();
        this.number = number;
    }

    @Override
    public void createPartControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new RowLayout());
        Text text1 = new Text(composite, SWT.NONE);
        //$NON-NLS-1$
        text1.setText("Blue");
        Text text2 = new Text(composite, SWT.NONE);
        //$NON-NLS-1$
        text2.setText("Red");
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
    // Do nothing.
    }

    @Override
    public void doSaveAs() {
        //$NON-NLS-1$
        throw new UnsupportedOperationException("Not implemented in this test.");
    }

    public void gotoMarker(IMarker marker) {
    // Do nothing.
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) {
        setInput(input);
        setSite(site);
        //$NON-NLS-1$
        setPartName("Editor");
        setTitleImage(input.getImageDescriptor().createImage());
        //$NON-NLS-1$
        setTitleToolTip("Moooooo");
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void setFocus() {
        final boolean odds = (number % 2) > 0;
        //$NON-NLS-1$
        final String scope1 = "org.eclipse.ui.tests.scope1";
        //$NON-NLS-1$
        final String scope2 = "org.eclipse.ui.tests.scope2";
        IKeyBindingService keyBindingService = getEditorSite().getKeyBindingService();
        keyBindingService.setScopes(new String[] { (odds) ? scope1 : scope2 });
    }
}

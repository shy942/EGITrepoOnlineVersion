/***/
package org.eclipse.ui.tests.multipageeditor;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
* A multi-page editor for testing key bindings while switching pages. This
* creates two pages -- each with a different context. The first context binds
* "Ctrl+Shift+4" -- the second binds "Ctrl+Shift+5" -- to the command
* "org.eclipse.ui.tests.TestCommand".
*
* @since 3.0
*/
public final class TestMultiPageEditor extends MultiPageEditorPart {

    @Override
    protected void createPages() {
        try {
            IEditorPart part1 = new TestKeyBindingMultiPageEditorPart(0);
            addPage(part1, getEditorInput());
            IEditorPart part2 = new TestKeyBindingMultiPageEditorPart(1);
            addPage(part2, getEditorInput());
        } catch (PartInitException e) {
            throw new RuntimeException(e);
        }
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
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
* Sets the active page.
*
* @param page
*            The page to activate; should be either <code>0</code> or
*            <code>1</code>.
*/
    public void setPage(int page) {
        setActivePage(page);
    }
}

/***/
package org.eclipse.ui.tests.api;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.tests.harness.util.CallHistory;
import org.eclipse.ui.tests.harness.util.FileUtil;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* This is a test for IEditorPart.  Since IEditorPart is an
* interface this test verifies the IEditorPart lifecycle rather
* than the implementation.
*/
public class IEditorActionBarContributorTest extends UITestCase {

    protected IWorkbenchWindow fWindow;

    protected IWorkbenchPage fPage;

    private String EDITOR_ID = "org.eclipse.ui.tests.api.IEditorActionBarContributorTest";

    /**
* Constructor for IEditorPartTest
*/
    public  IEditorActionBarContributorTest(String testName) {
        super(testName);
    }

    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        fWindow = openTestWindow();
        fPage = fWindow.getActivePage();
    }

    public void testInit() throws Throwable {
        // From javadoc: "Initializes this contributor, which is expected to
        // add contributions as required to the given action bars and global
        // action handlers."
        // Open a part.
        MockEditorPart part = openEditor(fPage, "1");
        // Get contributor and verify call history.
        MockEditorActionBarContributor contributor = (MockEditorActionBarContributor) part.getEditorSite().getActionBarContributor();
        CallHistory history = contributor.getCallHistory();
        assertTrue(history.verifyOrder(new String[] { "init", "setActiveEditor" }));
        assertEquals(part, contributor.getActiveEditor());
        // Close the part.
        fPage.closeAllEditors(false);
    }

    public void testSetActiveEditor() throws Throwable {
        // From javadoc: "Sets the active editor for the contributor."
        // Open an editor.
        MockEditorPart part = openEditor(fPage, "X");
        // Get contributor and verify call history.
        MockEditorActionBarContributor contributor = (MockEditorActionBarContributor) part.getEditorSite().getActionBarContributor();
        CallHistory history = contributor.getCallHistory();
        assertTrue(history.verifyOrder(new String[] { "init", "setActiveEditor" }));
        assertEquals(part, contributor.getActiveEditor());
        // Open a few more and verify setActiveEditor.
        int editorCount = 5;
        MockEditorPart[] parts = new MockEditorPart[editorCount];
        for (int nX = 0; nX < editorCount; nX++) {
            history.clear();
            parts[nX] = openEditor(fPage, Integer.toString(nX));
            assertTrue(history.verifyOrder(new String[] { "setActiveEditor" }));
            assertEquals(parts[nX], contributor.getActiveEditor());
        }
        // Activate the parts and verify setActiveEditor.
        for (int nX = 0; nX < editorCount; nX++) {
            history.clear();
            fPage.activate(parts[nX]);
            assertTrue(history.verifyOrder(new String[] { "setActiveEditor" }));
            assertEquals(parts[nX], contributor.getActiveEditor());
        }
        // Close the parts.
        fPage.closeAllEditors(false);
    }

    /**
* @see IWorkbenchPartTest#openPart(IWorkbenchPage)
*/
    protected MockEditorPart openEditor(IWorkbenchPage page, String suffix) throws Throwable {
        IProject proj = FileUtil.createProject("IEditorActionBarContributorTest");
        IFile file = FileUtil.createFile("test" + suffix + ".txt", proj);
        return (MockEditorPart) page.openEditor(new FileEditorInput(file), EDITOR_ID);
    }
}

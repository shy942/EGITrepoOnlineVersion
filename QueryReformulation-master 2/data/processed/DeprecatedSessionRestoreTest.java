/***/
package org.eclipse.ui.tests.api;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.tests.harness.util.EmptyPerspective;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* SessionRestoreTest runs the second half of our session
* presistance tests.
*
*/
public class DeprecatedSessionRestoreTest extends UITestCase {

    /**
* Construct an instance.
*/
    public  DeprecatedSessionRestoreTest(String arg) {
        super(arg);
    }

    /**
* Generates a session state in the workbench.
*/
    public void testRestoreSession() throws Throwable {
        IWorkbenchWindow[] windows;
        IWorkbenchPage[] pages;
        // Get windows.
        windows = fWorkbench.getWorkbenchWindows();
        assertEquals(windows.length, 3);
        // First window contains empty perspective.
        pages = windows[0].getPages();
        assertEquals(pages.length, 1);
        assertEquals(pages[0].getPerspective().getId(), EmptyPerspective.PERSP_ID);
        // Second window contains empty + session.
        pages = windows[1].getPages();
        assertEquals(pages.length, 2);
        assertEquals(pages[0].getPerspective().getId(), EmptyPerspective.PERSP_ID);
        assertEquals(pages[1].getPerspective().getId(), SessionPerspective.ID);
        testSessionView(pages[1]);
        // Third window contains 2 sessions.
        pages = windows[2].getPages();
        assertEquals(pages.length, 2);
        assertEquals(pages[0].getPerspective().getId(), SessionPerspective.ID);
        assertEquals(pages[1].getPerspective().getId(), SessionPerspective.ID);
        testSessionView(pages[0]);
        testSessionView(pages[1]);
        // Last page contains 3 editors.
        IEditorPart[] editors = pages[1].getEditors();
        assertEquals(editors.length, 3);
        testSessionEditor(editors[0], SessionCreateTest.TEST_FILE_1);
        testSessionEditor(editors[1], SessionCreateTest.TEST_FILE_2);
        testSessionEditor(editors[2], SessionCreateTest.TEST_FILE_3);
    }

    /**
* Tests the session view within a page.
*/
    private void testSessionView(IWorkbenchPage page) {
        IViewPart view = page.findView(SessionView.VIEW_ID);
        assertNotNull(view);
        SessionView sessionView = (SessionView) view;
        sessionView.testMementoState(this);
    }

    /**
* Tests the state of a session editor.
*/
    private void testSessionEditor(IEditorPart part, String fileName) {
        IEditorSite site = part.getEditorSite();
        assertEquals(site.getId(), MockEditorPart.ID1);
        IEditorInput input = part.getEditorInput();
        assertTrue(input instanceof IFileEditorInput);
        IFile file = ((IFileEditorInput) input).getFile();
        assertEquals(fileName, file.getName());
    }
}

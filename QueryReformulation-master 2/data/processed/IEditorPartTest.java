/***/
package org.eclipse.ui.tests.api;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.tests.harness.util.CallHistory;
import org.eclipse.ui.tests.harness.util.FileUtil;

/**
* This is a test for IEditorPart. Since IEditorPart is an interface this test
* verifies the IEditorPart lifecycle rather than the implementation.
*/
public class IEditorPartTest extends IWorkbenchPartTest {

    /**
* Constructor for IEditorPartTest
*/
    public  IEditorPartTest(String testName) {
        super(testName);
    }

    /**
* @see IWorkbenchPartTest#openPart(IWorkbenchPage)
*/
    @Override
    protected MockPart openPart(IWorkbenchPage page) throws Throwable {
        IProject proj = FileUtil.createProject("IEditorPartTest");
        IFile file = FileUtil.createFile("IEditorPartTest.txt", proj);
        return (MockWorkbenchPart) page.openEditor(new FileEditorInput(file), MockEditorPart.ID1);
    }

    /**
* @see IWorkbenchPartTest#closePart(IWorkbenchPage, MockWorkbenchPart)
*/
    @Override
    protected void closePart(IWorkbenchPage page, MockPart part) throws Throwable {
        page.closeEditor((IEditorPart) part, true);
    }

    /**
* Tests that the editor is closed without saving if isSaveOnCloseNeeded()
* returns false.
*
* @see ISaveablePart#isSaveOnCloseNeeded()
*/
    public void testOpenAndCloseSaveNotNeeded() throws Throwable {
        // Open a part.
        MockEditorPart part = (MockEditorPart) openPart(fPage);
        part.setDirty(true);
        part.setSaveNeeded(false);
        closePart(fPage, part);
        CallHistory history = part.getCallHistory();
        assertTrue(history.verifyOrder(new String[] { "setInitializationData", "init", "createPartControl", "setFocus", "isSaveOnCloseNeeded", "widgetDisposed", "dispose" }));
        assertFalse(history.contains("doSave"));
    }

    public void testOpenAndCloseWithNoMemento() throws Throwable {
        IProject proj = FileUtil.createProject("IEditorPartTest");
        IFile file = FileUtil.createFile("IEditorPartTest.txt", proj);
        MockEditorWithState editor = (MockEditorWithState) fPage.openEditor(new FileEditorInput(file), MockEditorWithState.ID);
        closePart(fPage, editor);
        CallHistory history = editor.getCallHistory();
        assertFalse(history.contains("saveState"));
        assertFalse(history.contains("restoreState"));
    }
}

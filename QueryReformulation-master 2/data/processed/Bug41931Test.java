/***/
package org.eclipse.ui.tests.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Test for Bug 41931.
*
* @since 3.0
*/
public class Bug41931Test extends UITestCase {

    /**
* Constructs a new instance of this test case.
*
* @param testName
*            The name of the test
*/
    public  Bug41931Test(String testName) {
        super(testName);
    }

    /**
* Tests that the <code>bringToTop(IWorkbenchPart)</code> correctly
* updates the activation list.
*
* @throws CoreException
*             If the test project cannot be created or opened.
*/
    public void testBringToTop() throws CoreException {
        // Open a window.
        IWorkbenchWindow window = openTestWindow();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        // Create a test project.
        //$NON-NLS-1$
        IProject testProject = workspace.getRoot().getProject("Bug41931");
        testProject.create(null);
        testProject.open(null);
        // Open three test files.
        InputStream contents = new ByteArrayInputStream(new byte[0]);
        //$NON-NLS-1$
        IFile fileA = testProject.getFile("a.txt");
        fileA.create(contents, true, null);
        //$NON-NLS-1$
        IFile fileB = testProject.getFile("b.txt");
        fileB.create(contents, true, null);
        //$NON-NLS-1$
        IFile fileC = testProject.getFile("c.txt");
        fileC.create(contents, true, null);
        // Open editors on those files.
        WorkbenchPage page = (WorkbenchPage) window.getActivePage();
        IEditorPart editorA = IDE.openEditor(page, fileA, true);
        IEditorPart editorB = IDE.openEditor(page, fileB, true);
        IEditorPart editorC = IDE.openEditor(page, fileC, true);
        // Test that the editors are open in the order: A, B, C
        IEditorPart[] expectedResults = { editorA, editorB, editorC };
        IWorkbenchPartReference[] actualResults = page.getSortedParts();
        for (int i = 0; i < expectedResults.length; i++) {
            assertEquals("Pre-test order is not correct.", expectedResults[i].getTitle(), //$NON-NLS-1$
            actualResults[i].getPart(false).getTitle());
        }
        // Bring editor B to the top.
        page.bringToTop(editorB);
        // Test that the editors are open in the order: A, C, B
        expectedResults = new IEditorPart[] { editorA, editorC, editorB };
        actualResults = page.getSortedParts();
        for (int i = 0; i < expectedResults.length; i++) {
            assertEquals("bringToTop() does not change sorted part order.", expectedResults[i].getTitle(), //$NON-NLS-1$
            actualResults[i].getPart(false).getTitle());
        }
    }
}

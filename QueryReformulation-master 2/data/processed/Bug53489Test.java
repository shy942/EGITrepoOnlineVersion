/***/
package org.eclipse.ui.tests.keys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.CommandException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.tests.harness.util.AutomationUtil;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests that pressing delete in a styled text widget does not cause a double
* delete situation.
*
* @since 3.0
*/
public class Bug53489Test extends UITestCase {

    /**
* Constructor for Bug53489Test.
*
* @param name
*            The name of the test
*/
    public  Bug53489Test(String name) {
        super(name);
    }

    /**
* Tests that pressing delete in a styled text widget (in a running
* Eclipse) does not cause a double delete.
*
* @throws AWTException
*             If the creation of robot
* @throws CommandException
*             If execution of the handler fails.
* @throws CoreException
*             If the test project cannot be created and opened.
* @throws IOException
*             If the file cannot be read.
*/
    public void testDoubleDelete() throws CommandException, CoreException, IOException {
        IWorkbenchWindow window = openTestWindow();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject testProject = workspace.getRoot().getProject(//$NON-NLS-1$
        "DoubleDeleteestProject");
        testProject.create(null);
        testProject.open(null);
        //$NON-NLS-1$
        IFile textFile = testProject.getFile("A.txt");
        //$NON-NLS-1$
        String originalContents = "A blurb";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(originalContents.getBytes());
        textFile.create(inputStream, true, null);
        IDE.openEditor(window.getActivePage(), textFile, true);
        // Allow the editor to finish opening.
        Display display = Display.getCurrent();
        while (display.readAndDispatch()) {
            ;
        }
        AutomationUtil.performKeyCodeEvent(display, SWT.KeyDown, SWT.DEL);
        AutomationUtil.performKeyCodeEvent(display, SWT.KeyUp, SWT.DEL);
        AutomationUtil.performKeyCodeEvent(display, SWT.KeyDown, SWT.CTRL);
        AutomationUtil.performCharacterEvent(display, SWT.KeyDown, 'S');
        AutomationUtil.performCharacterEvent(display, SWT.KeyUp, 'S');
        AutomationUtil.performKeyCodeEvent(display, SWT.KeyUp, SWT.CTRL);
        // Spin the event loop.
        while (display.readAndDispatch()) {
            ;
        }
        // Test the text is only one character different.
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(textFile.getContents()));
        String currentContents = reader.readLine();
        assertTrue("'DEL' deleted more than one key.", (//$NON-NLS-1$
        originalContents.length() == (currentContents.length() + 1)));
    }
}

/***/
package org.eclipse.ui.tests.internal;

import java.io.ByteArrayInputStream;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.operations.AdvancedValidationUserApprover;
import org.eclipse.ui.views.navigator.ResourceNavigator;

/**
* bug 99858 [IDE] Error upon deleting a project. Tests that our delete code no
* longer throws a CoreException when deleting a closed project.
*
* @since 3.2
*/
public class Bug99858Test extends TestCase {

    private static final String NAVIGATOR_VIEW = "org.eclipse.ui.views.ResourceNavigator";

    public static TestSuite suite() {
        return new TestSuite(Bug99858Test.class);
    }

    public  Bug99858Test() {
        super();
    }

    public  Bug99858Test(String name) {
        super(name);
    }

    /**
* Create a project with some files, close it, and delete it. With the
* changes in runtime to throw a CoreException from IContainer#members(),
* the project won't get deleted if ReadOnlyStateChecker is not fixed.
*
* @throws Throwable
*             if it goes wrong
*/
    public void testDeleteClosedProject() throws Throwable {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject testProject = workspace.getRoot().getProject("TestClosedDelete");
        testProject.create(null);
        testProject.open(null);
        String contents = "File ready for execution, sir!";
        createProjectFile(testProject, "a.txt", contents);
        createProjectFile(testProject, "b.txt", contents);
        ResourceNavigator view = (ResourceNavigator) page.showView(NAVIGATOR_VIEW);
        view.setFocus();
        // get a testing version of the delete action, and set it up.
        MyDeleteResourceAction newDel = new MyDeleteResourceAction(view.getViewSite());
        newDel.setEnabled(true);
        TextActionHandler tmpHandler = new TextActionHandler(view.getViewSite().getActionBars());
        tmpHandler.setDeleteAction(newDel);
        view.getViewSite().getActionBars().updateActionBars();
        chewUpEvents();
        StructuredSelection s = new StructuredSelection(testProject);
        // close the project and update the selection events.
        testProject.close(null);
        assertFalse(testProject.isAccessible());
        view.getViewSite().getSelectionProvider().setSelection(s);
        newDel.selectionChanged(s);
        chewUpEvents();
        IAction del = view.getViewSite().getActionBars().getGlobalActionHandler(ActionFactory.DELETE.getId());
        assertTrue(del.isEnabled());
        // run the delete event.
        del.runWithEvent(null);
        chewUpEvents();
        // the delete even ran
        assertTrue(newDel.fRan);
        // Join twice as there are two jobs now
        boolean joined = false;
        while (!joined) {
            try {
                Platform.getJobManager().join(IDEWorkbenchMessages.DeleteResourceAction_jobName, null);
                joined = true;
            } catch (InterruptedException ex) {
                chewUpEvents();
            }
        }
        joined = false;
        while (!joined) {
            try {
                Platform.getJobManager().join(IDEWorkbenchMessages.DeleteResourceAction_jobName, null);
                joined = true;
            } catch (InterruptedException ex) {
                chewUpEvents();
            }
        }
        // if our project still exists, the delete failed.
        assertFalse(testProject.exists());
    }

    /**
* Subclass the delete action and go into testing mode, which limits user
* dialogs.
*
* @since 3.2
*/
    private class MyDeleteResourceAction extends DeleteResourceAction {

        public boolean fRan = false;

        public  MyDeleteResourceAction(IShellProvider provider) {
            super(provider);
            fTestingMode = true;
        }

        @Override
        public void run() {
            super.run();
            fRan = true;
        }
    }

    /**
* Create a quick project file, so the project has some children to delete.
*
* @param testProject
*            the project
* @param name
*            the filename
* @param contents
*            A small string for contents
* @throws CoreException
*             if IFile#create(...) throws an exception
*/
    private void createProjectFile(IProject testProject, String name, String contents) throws CoreException {
        IFile textFile = testProject.getFile(name);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(contents.getBytes());
        textFile.create(inputStream, true, null);
    }

    /**
* After an internal action, see if there are any outstanding SWT events.
*/
    private void chewUpEvents() {
        Display display = Display.getCurrent();
        while (display.readAndDispatch()) {
            ;
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        AdvancedValidationUserApprover.AUTOMATED_MODE = true;
    }

    @Override
    protected void tearDown() throws Exception {
        AdvancedValidationUserApprover.AUTOMATED_MODE = false;
        super.tearDown();
    }
}

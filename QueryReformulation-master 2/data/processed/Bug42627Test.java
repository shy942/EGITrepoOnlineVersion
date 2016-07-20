/***/
package org.eclipse.ui.tests.keys;

import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests Bug 42627
*
* @since 3.0
*/
public class Bug42627Test extends UITestCase {

    /**
* Constructor for Bug42627Test.
*
* @param name
*           The name of the test
*/
    public  Bug42627Test(String name) {
        super(name);
    }

    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
    //		logged = false;
    }

    /**
* Tests that actions with no defined command ID are logged.
*
* @throws CoreException
*            If something fails when trying to open a new project.
*/
    public void testLogUndefined() /*throws CoreException*/
    {
    // TODO No log is being generated.  What was Chris' fix?
    //		IWorkbenchWindow window = openTestWindow();
    //		ResourcesPlugin.getPlugin().getLog().addLogListener(new ILogListener() {
    //			public void logging(IStatus status, String string) {
    //				logged = true;
    //			}
    //		});
    //		IWorkspace workspace = ResourcesPlugin.getWorkspace();
    //		IProject testProject = workspace.getRoot().getProject("Bug42627Project"); //$NON-NLS-1$
    //		testProject.create(null);
    //		testProject.open(null);
    //		AbstractTextEditor editor = (AbstractTextEditor) window.getActivePage().openEditor(testProject.getFile(".project")); //$NON-NLS-1$
    //		editor.selectAndReveal(0, 1);
    //		EditorSite site = (EditorSite) editor.getSite();
    //		site.getActionBars().setGlobalActionHandler("Bogus action name that hopefully will not exist", new DummyAction()); //$NON-NLS-1$
    //		window.getShell().setFocus();
    //		assertTrue("Nothing has been logged.", logged); //$NON-NLS-1$
    }
}

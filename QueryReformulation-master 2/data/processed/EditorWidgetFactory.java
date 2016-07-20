/***/
package org.eclipse.ui.tests.performance.layout;

import junit.framework.Assert;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.EditorSite;
import org.eclipse.ui.tests.harness.util.EmptyPerspective;
import org.eclipse.ui.tests.harness.util.UITestCase;
import org.eclipse.ui.tests.performance.UIPerformanceTestSetup;

/**
* @since 3.1
*/
public class EditorWidgetFactory extends TestWidgetFactory {

    private String editorId;

    private String filename;

    private IWorkbenchWindow window;

    private Composite ctrl;

    public  EditorWidgetFactory(String filename) {
        this.filename = filename;
        this.editorId = null;
    }

    public  EditorWidgetFactory(String filename, String editorId) {
        this.filename = filename;
        this.editorId = editorId;
    }

    public static Composite getControl(IEditorPart part) {
        EditorSite site = (EditorSite) part.getSite();
        MPart modelPart = site.getModel();
        return (Composite) modelPart.getWidget();
    }

    @Override
    public String getName() {
        return "editor " + filename + (editorId != null ? editorId : "");
    }

    @Override
    public void init() throws CoreException, WorkbenchException {
        // Open an editor in a new window.
        window = PlatformUI.getWorkbench().openWorkbenchWindow(EmptyPerspective.PERSP_ID, UITestCase.getPageInput());
        IWorkbenchPage activePage = window.getActivePage();
        Assert.assertNotNull(activePage);
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject testProject = workspace.getRoot().getProject(UIPerformanceTestSetup.PROJECT_NAME);
        IFile file = testProject.getFile(filename);
        if (editorId == null) {
            editorId = IDE.getEditorDescriptor(file).getId();
        }
        IEditorPart part = IDE.openEditor(activePage, file, editorId, true);
        ctrl = getControl(part);
    }

    @Override
    public Composite getControl() throws CoreException, WorkbenchException {
        return ctrl;
    }

    @Override
    public void done() throws CoreException, WorkbenchException {
        window.close();
        super.done();
    }
}

/***/
package org.eclipse.ui.tests.adaptable;

import java.io.ByteArrayInputStream;
import junit.framework.TestSuite;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.decorators.DecoratorDefinition;
import org.eclipse.ui.internal.decorators.DecoratorManager;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* @version 1.0
*/
public class AdaptableDecoratorTestCase extends UITestCase implements ILabelProviderListener {

    private DecoratorDefinition fullDefinition;

    private DecoratorDefinition lightDefinition;

    private boolean updated = false;

    public String ADAPTED_NAVIGATOR_ID = "org.eclipse.ui.tests.adaptable.adaptedHierarchy";

    protected IProject testProject;

    protected IFolder testFolder;

    protected IFile testFile;

    public static TestSuite suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new AdaptableDecoratorTestCase("testEnableDecorator"));
        ts.addTest(new AdaptableDecoratorTestCase("testDisableDecorator"));
        ts.addTest(new AdaptableDecoratorTestCase("testRefreshFullContributor"));
        ts.addTest(new AdaptableDecoratorTestCase("testRefreshLightContributor"));
        return ts;
    }

    /**
* Constructor for DecoratorTestCase.
*
* @param testName
*/
    public  AdaptableDecoratorTestCase(String testName) {
        super(testName);
    }

    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        createTestFile();
        showAdaptedNav();
        WorkbenchPlugin.getDefault().getDecoratorManager().addListener(this);
        DecoratorDefinition[] definitions = WorkbenchPlugin.getDefault().getDecoratorManager().getAllDecoratorDefinitions();
        for (DecoratorDefinition definition : definitions) {
            if (definition.getId().equals("org.eclipse.ui.tests.adaptable.decorator")) {
                fullDefinition = definition;
            }
            if (definition.getId().equals("org.eclipse.ui.tests.decorators.lightweightdecorator")) {
                lightDefinition = definition;
            }
        }
    }

    private DecoratorManager getDecoratorManager() {
        return WorkbenchPlugin.getDefault().getDecoratorManager();
    }

    @Override
    protected void doTearDown() throws Exception {
        if (testProject != null) {
            try {
                testProject.delete(true, null);
            } catch (CoreException e) {
                fail(e.toString());
            }
            testProject = null;
            testFolder = null;
            testFile = null;
        }
        super.doTearDown();
        getDecoratorManager().removeListener(this);
    }

    /**
* Test enabling the contributor
*/
    public void testEnableDecorator() {
        getDecoratorManager().updateForEnablementChange();
        fullDefinition.setEnabled(true);
        lightDefinition.setEnabled(true);
        getDecoratorManager().updateForEnablementChange();
    }

    /**
* Test disabling the contributor
*/
    public void testDisableDecorator() {
        getDecoratorManager().updateForEnablementChange();
        fullDefinition.setEnabled(false);
        lightDefinition.setEnabled(false);
        getDecoratorManager().updateForEnablementChange();
    }

    /**
* Refresh the full decorator.
*/
    public void testRefreshFullContributor() {
        updated = false;
        getDecoratorManager().updateForEnablementChange();
        fullDefinition.setEnabled(true);
        lightDefinition.setEnabled(false);
        getDecoratorManager().updateForEnablementChange();
        assertTrue("Got an update", updated);
        updated = false;
    }

    /**
* Refresh the full decorator.
*/
    public void testRefreshLightContributor() {
        updated = false;
        getDecoratorManager().updateForEnablementChange();
        lightDefinition.setEnabled(true);
        fullDefinition.setEnabled(false);
        getDecoratorManager().updateForEnablementChange();
        assertTrue("Got an update", updated);
        updated = false;
    }

    @Override
    public void labelProviderChanged(LabelProviderChangedEvent event) {
        updated = true;
    }

    /**
* Shows the Adapted Resource Navigator in a new test window.
*/
    protected void showAdaptedNav() throws PartInitException {
        IWorkbenchWindow window = openTestWindow();
        window.getActivePage().showView(ADAPTED_NAVIGATOR_ID);
    }

    protected void createTestProject() throws CoreException {
        if (testProject == null) {
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            testProject = workspace.getRoot().getProject("AdaptedTestProject");
            testProject.create(null);
            testProject.open(null);
        }
    }

    protected void createTestFolder() throws CoreException {
        if (testFolder == null) {
            createTestProject();
            testFolder = testProject.getFolder("AdaptedTestFolder");
            testFolder.create(false, false, null);
        }
    }

    protected void createTestFile() throws CoreException {
        if (testFile == null) {
            createTestFolder();
            testFile = testFolder.getFile("AdaptedFoo.txt");
            testFile.create(new ByteArrayInputStream("Some content.".getBytes()), false, null);
        }
    }
}

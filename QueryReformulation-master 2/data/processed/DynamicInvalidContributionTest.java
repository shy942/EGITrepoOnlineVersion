/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* @since 3.5
*/
public class DynamicInvalidContributionTest extends DynamicTestCase {

    public  DynamicInvalidContributionTest(String testName) {
        super(testName);
    }

    public void testInvalidMenuContribution() throws Exception {
        // open a window
        IWorkbenchWindow window = openTestWindow();
        // start up our bundle
        getBundle();
        // open another window, now that our invalid contribution is there, it
        // should be parsed and loaded, this ensures the workbench window can
        // still go up even if someone is contributing an invalid contribution
        fWorkbench.openWorkbenchWindow(window.getActivePage().getPerspective().getId(), null);
    }

    @Override
    protected String getExtensionId() {
        return "menu.invalid.menu.contribution";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_MENUS;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newInvalidMenuContribution1";
    }
}

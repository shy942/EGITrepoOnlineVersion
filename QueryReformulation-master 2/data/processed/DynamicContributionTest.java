/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.menus.IMenuService;

/**
* @since 3.5
*/
public class DynamicContributionTest extends DynamicTestCase {

    public  DynamicContributionTest(String testName) {
        super(testName);
    }

    public void testMenuContribution() throws Exception {
        IWorkbenchWindow window = openTestWindow();
        IMenuService menus = window.getService(IMenuService.class);
        MenuManager manager = new MenuManager();
        try {
            menus.populateContributionManager(manager, "popup:org.eclipse.newDynamicMenuContribution");
            assertEquals(0, manager.getSize());
            getBundle();
            assertEquals(1, manager.getSize());
            assertFalse(BundleUtility.isActive("org.eclipse.newDynamicMenuContribution"));
            manager.createContextMenu(window.getShell());
            manager.updateAll(true);
            assertTrue(BundleUtility.isActive("org.eclipse.newDynamicMenuContribution"));
        } finally {
            menus.releaseContributions(manager);
        }
    }

    @Override
    protected String getExtensionId() {
        return "menu.dynamic.contribution";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_MENUS;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newDynamicMenuContribution";
    }
}

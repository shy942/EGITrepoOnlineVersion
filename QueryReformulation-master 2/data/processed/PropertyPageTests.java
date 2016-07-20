/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.ui.internal.dialogs.PropertyPageContributorManager;
import org.eclipse.ui.internal.dialogs.PropertyPageManager;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* @since 3.1
*/
public class PropertyPageTests extends DynamicTestCase {

    private static final String PROPERTYPAGE = "dynamicPropertyPage1";

    /**
* @param testName
*/
    public  PropertyPageTests(String testName) {
        super(testName);
    }

    public void testPropertyPageCount() {
        PropertyPageContributorManager manager = PropertyPageContributorManager.getManager();
        int size = manager.getContributors().size();
        getBundle();
        assertEquals(size + 1, manager.getContributors().size());
        removeBundle();
        assertEquals(size, manager.getContributors().size());
    }

    public void testPropertyPageContribution() {
        PropertyPageContributorManager cManager = PropertyPageContributorManager.getManager();
        PropertyPageManager manager;
        DynamicTestType type = new DynamicTestType();
        cManager.contribute(manager = new PropertyPageManager(), type);
        assertNull(manager.find(PROPERTYPAGE));
        getBundle();
        cManager.contribute(manager = new PropertyPageManager(), type);
        IPreferenceNode result = manager.find(PROPERTYPAGE);
        assertNotNull(result);
        // muck around and ensure we've created some potential garbage
        result.createPage();
        result.disposeResources();
        removeBundle();
        cManager.contribute(manager = new PropertyPageManager(), type);
        assertNull(manager.find(PROPERTYPAGE));
    }

    @Override
    protected String getExtensionId() {
        return "newPropertyPage1.testDynamicPropertyPageAddition";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_PROPERTY_PAGES;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newPropertyPage1";
    }

    @Override
    protected String getMarkerClass() {
        return "org.eclipse.ui.dynamic.DynamicPropertyPage";
    }
}

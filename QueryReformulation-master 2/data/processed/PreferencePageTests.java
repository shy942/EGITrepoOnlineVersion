/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.dialogs.WorkbenchPreferenceNode;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* @since 3.1
*/
public class PreferencePageTests extends DynamicTestCase {

    /**
* @param testName
*/
    public  PreferencePageTests(String testName) {
        super(testName);
    }

    public void testPreferences() {
        PreferenceManager preferenceManager = PlatformUI.getWorkbench().getPreferenceManager();
        assertNull(preferenceManager.find("dynamic.parentPage"));
        assertNull(preferenceManager.find("dynamic.parentPage/dynamic.childPage"));
        getBundle();
        WorkbenchPreferenceNode node = (WorkbenchPreferenceNode) preferenceManager.find("dynamic.parentPage");
        assertNotNull(node);
        //make sure we have a page
        node.createPage();
        node = (WorkbenchPreferenceNode) preferenceManager.find("dynamic.parentPage/dynamic.childPage");
        assertNotNull(node);
        //make sure we have a page
        node.createPage();
        removeBundle();
        assertNull(preferenceManager.find("dynamic.parentPage"));
        assertNull(preferenceManager.find("dynamic.parentPage/dynamic.childPage"));
    }

    @Override
    protected String getExtensionId() {
        return "newPreferencePage1.testDynamicPreferencePageAddition";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_PREFERENCES;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newPreferencePage1";
    }

    @Override
    protected String getMarkerClass() {
        return "org.eclipse.ui.dynamic.DynamicPreferencePage";
    }
}

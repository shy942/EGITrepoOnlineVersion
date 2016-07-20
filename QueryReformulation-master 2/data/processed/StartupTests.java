/***/
package org.eclipse.ui.tests.dynamicplugins;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.osgi.framework.Bundle;

/**
* @since 3.1
*/
public class StartupTests extends DynamicTestCase {

    /**
* @param testName
*/
    public  StartupTests(String testName) {
        super(testName);
    }

    /**
* Tests to ensure that the IStartup implementation in the bundle is run
* when the bundle is loaded.
*
* @throws ClassNotFoundException
* @throws SecurityException
* @throws NoSuchFieldException
* @throws IllegalArgumentException
* @throws IllegalAccessException
*/
    public void testStartupRun() throws ClassNotFoundException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Bundle bundle = getBundle();
        Class clazz = bundle.loadClass(getMarkerClass());
        assertNotNull(clazz);
        Field field = clazz.getDeclaredField("history");
        assertNotNull(field);
        assertTrue((field.getModifiers() & Modifier.STATIC) != 0);
        // if the startup code has run then this will not be null - the early
        // startup method sets this
        assertNotNull(field.get(null));
    }

    @Override
    protected String getExtensionId() {
        return "newStartup1.testDynamicStartupAddition";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_STARTUP;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newStartup1";
    }

    @Override
    protected String getMarkerClass() {
        return "org.eclipse.ui.dynamic.DynamicStartup";
    }
}

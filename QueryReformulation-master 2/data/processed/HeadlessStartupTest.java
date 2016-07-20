/***/
package org.eclipse.e4.ui.tests.application;

import org.eclipse.e4.core.commands.CommandServiceAddon;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.services.ContextServiceAddon;
import org.junit.After;
import org.junit.Before;

public abstract class HeadlessStartupTest {

    protected IEclipseContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = createApplicationContext();
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.dispose();
    }

    protected IEclipseContext createApplicationContext() {
        final IEclipseContext appContext = E4Application.createDefaultContext();
        ContextInjectionFactory.make(CommandServiceAddon.class, appContext);
        ContextInjectionFactory.make(ContextServiceAddon.class, appContext);
        return appContext;
    }
}

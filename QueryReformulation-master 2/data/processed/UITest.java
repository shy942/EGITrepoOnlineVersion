/***/
package org.eclipse.e4.ui.tests.application;

import static org.junit.Assert.assertTrue;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.contributions.IContributionFactory;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.impl.ApplicationFactoryImpl;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.junit.After;
import org.junit.Before;

public class UITest {

    //$NON-NLS-1$
    private static final String engineURI = "bundleclass://org.eclipse.e4.ui.tests/org.eclipse.e4.ui.tests.application.HeadlessContextPresentationEngine";

    protected IEclipseContext applicationContext;

    protected MApplication application;

    private IPresentationEngine engine;

    protected EModelService ems;

    @Before
    public void setUp() throws Exception {
        application = ApplicationFactoryImpl.eINSTANCE.createApplication();
        applicationContext = E4Application.createDefaultContext();
        application.setContext(applicationContext);
        applicationContext.set(MApplication.class, application);
        ems = applicationContext.get(EModelService.class);
        E4Application.initializeServices(application);
    }

    @After
    public void tearDown() throws Exception {
        // used by the tests to dispose GUI?
        applicationContext.dispose();
    }

    protected IPresentationEngine getEngine() {
        if (engine == null) {
            IContributionFactory contributionFactory = applicationContext.get(IContributionFactory.class);
            Object newEngine = contributionFactory.create(engineURI, applicationContext);
            assertTrue(newEngine instanceof IPresentationEngine);
            applicationContext.set(IPresentationEngine.class.getName(), newEngine);
            engine = (IPresentationEngine) newEngine;
        }
        return engine;
    }
}

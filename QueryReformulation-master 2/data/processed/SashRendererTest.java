/***/
package org.eclipse.e4.ui.tests.workbench;

import static org.junit.Assert.assertEquals;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SashRendererTest {

    private IEclipseContext appContext;

    private E4Workbench wb;

    private EModelService ems;

    @Before
    public void setUp() throws Exception {
        appContext = E4Application.createDefaultContext();
        appContext.set(E4Workbench.PRESENTATION_URI_ARG, PartRenderingEngine.engineURI);
        ems = appContext.get(EModelService.class);
    }

    @After
    public void tearDown() throws Exception {
        if (wb != null) {
            wb.close();
        }
        appContext.dispose();
    }

    @Test
    public void testBug310027() {
        MApplication application = ems.createModelElement(MApplication.class);
        MWindow window = ems.createModelElement(MWindow.class);
        MPartSashContainer container = ems.createModelElement(MPartSashContainer.class);
        MPartStack partStackA = ems.createModelElement(MPartStack.class);
        MPartStack partStackB = ems.createModelElement(MPartStack.class);
        MPart partA = ems.createModelElement(MPart.class);
        MPart partB = ems.createModelElement(MPart.class);
        window.setWidth(600);
        window.setHeight(400);
        partStackA.setContainerData("50");
        partStackB.setContainerData("50");
        application.getChildren().add(window);
        application.setSelectedElement(window);
        window.getChildren().add(container);
        window.setSelectedElement(container);
        container.getChildren().add(partStackA);
        container.getChildren().add(partStackB);
        container.setSelectedElement(partStackA);
        partStackA.getChildren().add(partA);
        partStackA.setSelectedElement(partA);
        partStackA.getChildren().add(partB);
        partStackA.setSelectedElement(partB);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        assertEquals("50", partStackA.getContainerData());
        assertEquals("50", partStackB.getContainerData());
        partStackB.setToBeRendered(false);
        while (Display.getDefault().readAndDispatch()) ;
        assertEquals("50", partStackA.getContainerData());
    }
}

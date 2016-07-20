/***/
package org.eclipse.e4.ui.tests.workbench;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MPartTest {

    protected IEclipseContext appContext;

    protected E4Workbench wb;

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
    public void testSetName() {
        final MWindow window = createWindowWithOneView("Part Name");
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        MPart part = (MPart) stack.getChildren().get(0);
        CTabFolder folder = (CTabFolder) stack.getWidget();
        CTabItem item = folder.getItem(0);
        assertEquals("Part Name", item.getText());
        part.setLabel("Another Name");
        assertEquals("Another Name", item.getText());
    }

    @Test
    public void testCTabItem_GetImage() {
        final MWindow window = createWindowWithOneView("Part Name");
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        CTabFolder folder = (CTabFolder) stack.getWidget();
        CTabItem item = folder.getItem(0);
        assertNotNull(item.getImage());
    }

    private void testDeclaredName(String declared, String expected) {
        final MWindow window = createWindowWithOneView(declared);
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        CTabFolder folder = (CTabFolder) stack.getWidget();
        CTabItem item = folder.getItem(0);
        assertEquals(expected, item.getText());
    }

    @Test
    public void testDeclaredNameNull() {
        testDeclaredName(null, "");
    }

    @Test
    public void testDeclaredNameEmpty() {
        testDeclaredName("", "");
    }

    @Test
    public void testDeclaredNameDefined() {
        testDeclaredName("partName", "partName");
    }

    private void testDeclaredTooltip(String partToolTip, String expectedToolTip) {
        final MWindow window = createWindowWithOneView("Part Name", partToolTip);
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        CTabFolder folder = (CTabFolder) stack.getWidget();
        CTabItem item = folder.getItem(0);
        assertEquals(expectedToolTip, item.getToolTipText());
    }

    @Test
    public void testDeclaredTooltipNull() {
        testDeclaredTooltip(null, null);
    }

    @Test
    public void testDeclaredTooltipEmptyString() {
        testDeclaredTooltip("", null);
    }

    @Test
    public void testDeclaredTooltipDefined() {
        testDeclaredTooltip("partToolTip", "partToolTip");
    }

    private void testMPart_setTooltip(String partToolTip, String expectedToolTip) {
        final MWindow window = createWindowWithOneView("Part Name");
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        MPart part = (MPart) stack.getChildren().get(0);
        CTabFolder folder = (CTabFolder) stack.getWidget();
        CTabItem item = folder.getItem(0);
        assertEquals(null, item.getToolTipText());
        part.setTooltip(partToolTip);
        assertEquals(expectedToolTip, item.getToolTipText());
    }

    @Test
    public void testMPart_setTooltipNull() {
        testMPart_setTooltip(null, null);
    }

    @Test
    public void testMPart_setTooltipEmptyString() {
        testMPart_setTooltip("", null);
    }

    @Test
    public void testMPart_setTooltipDefined() {
        testMPart_setTooltip("partToolTip", "partToolTip");
    }

    @Test
    public void testMPart_getContext() {
        final MWindow window = createWindowWithOneView("Part Name");
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        MPart part = (MPart) stack.getChildren().get(0);
        IPresentationEngine renderer = appContext.get(IPresentationEngine.class);
        renderer.removeGui(part);
        assertNull(part.getContext());
    }

    @Test
    public void testMPartBug369866() {
        final MWindow window = createWindowWithOneView("Part");
        MApplication application = ems.createModelElement(MApplication.class);
        application.getChildren().add(window);
        application.setContext(appContext);
        appContext.set(MApplication.class, application);
        wb = new E4Workbench(application, appContext);
        wb.createAndRunUI(window);
        MPartSashContainer container = (MPartSashContainer) window.getChildren().get(0);
        MPartStack stack = (MPartStack) container.getChildren().get(0);
        MPart part = (MPart) stack.getChildren().get(0);
        CTabFolder folder = (CTabFolder) stack.getWidget();
        CTabItem item = folder.getItem(0);
        // bug 369866 has a StringIOOBE from toggling the dirty flag with an
        // empty part name
        assertFalse(part.isDirty());
        assertEquals("Part", item.getText());
        part.setDirty(true);
        assertEquals("*Part", item.getText());
        part.setLabel("");
        assertEquals("*", item.getText());
        part.setDirty(false);
        assertEquals("", item.getText());
        part.setDirty(true);
        assertEquals("*", item.getText());
    }

    private MWindow createWindowWithOneView(String partName) {
        return createWindowWithOneView(partName, null);
    }

    private MWindow createWindowWithOneView(String partName, String toolTip) {
        final MWindow window = ems.createModelElement(MWindow.class);
        window.setHeight(300);
        window.setWidth(400);
        window.setLabel("MyWindow");
        MPartSashContainer sash = ems.createModelElement(MPartSashContainer.class);
        window.getChildren().add(sash);
        MPartStack stack = ems.createModelElement(MPartStack.class);
        sash.getChildren().add(stack);
        MPart contributedPart = ems.createModelElement(MPart.class);
        stack.getChildren().add(contributedPart);
        contributedPart.setLabel(partName);
        contributedPart.setTooltip(toolTip);
        contributedPart.setIconURI("platform:/plugin/org.eclipse.e4.ui.tests/icons/filenav_nav.png");
        contributedPart.setContributionURI("bundleclass://org.eclipse.e4.ui.tests/org.eclipse.e4.ui.tests.workbench.SampleView");
        return window;
    }
}

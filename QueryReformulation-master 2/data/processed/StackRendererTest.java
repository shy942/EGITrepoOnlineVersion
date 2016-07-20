/***/
package org.eclipse.e4.ui.workbench.renderers.swt;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.internal.workbench.swt.CSSConstants;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackRendererTest {

    private IEclipseContext context;

    private E4Workbench wb;

    private MPart part;

    private CTabItemStylingMethodsListener executedMethodsListener;

    private MPartStack partStack;

    private EModelService ems;

    @Before
    public void setUp() throws Exception {
        context = E4Application.createDefaultContext();
        context.set(E4Workbench.PRESENTATION_URI_ARG, PartRenderingEngine.engineURI);
        ems = context.get(EModelService.class);
        MApplication application = ems.createModelElement(MApplication.class);
        MWindow window = ems.createModelElement(MWindow.class);
        partStack = ems.createModelElement(MPartStack.class);
        part = ems.createModelElement(MPart.class);
        part.setLabel("some title");
        application.getChildren().add(window);
        application.setSelectedElement(window);
        window.getChildren().add(partStack);
        partStack.getChildren().add(part);
        application.setContext(context);
        context.set(MApplication.class, application);
        executedMethodsListener = new CTabItemStylingMethodsListener(part);
        wb = new E4Workbench(application, context);
        wb.getContext().set(IStylingEngine.class, (IStylingEngine) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { IStylingEngine.class }, executedMethodsListener));
        wb.createAndRunUI(window);
        while (Display.getDefault().readAndDispatch()) ;
    }

    @After
    public void tearDown() throws Exception {
        if (wb != null) {
            wb.close();
        }
        context.dispose();
    }

    @Test
    public void testTabStateHandlerWhenOneOfSupportedTagChangeEvents() throws Exception {
        // given
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put(UIEvents.EventTags.ELEMENT, part);
        params.put(UIEvents.EventTags.NEW_VALUE, CSSConstants.CSS_BUSY_CLASS);
        params.put(UIEvents.EventTags.OLD_VALUE, null);
        // when
        context.get(EventBroker.class).send(UIEvents.ApplicationElement.TOPIC_TAGS.replace(UIEvents.ALL_SUB_TOPICS, UIEvents.EventTypes.SET), params);
        // then
        assertEquals(1, executedMethodsListener.getMethodExecutionCount("setClassnameAndId(.+)"));
    }

    @Test
    public void testTabStateHandlerWhenSelectionChangedEvent() throws Exception {
        // given
        MPlaceholder placeHolder = ems.createModelElement(MPlaceholder.class);
        placeHolder.setRef(part);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put(UIEvents.EventTags.ELEMENT, partStack);
        params.put(UIEvents.EventTags.NEW_VALUE, placeHolder);
        params.put(UIEvents.EventTags.OLD_VALUE, null);
        // when
        context.get(EventBroker.class).send(UIEvents.ElementContainer.TOPIC_SELECTEDELEMENT.replace(UIEvents.ALL_SUB_TOPICS, UIEvents.EventTypes.SET), params);
        // then
        assertEquals(1, executedMethodsListener.getMethodExecutionCount("setClassnameAndId(.+)"));
    }

    // helper functions
    private static class CTabItemStylingMethodsListener implements InvocationHandler {

        private MPart part;

        private List<String> methods;

        public  CTabItemStylingMethodsListener(MPart part) {
            this.part = part;
            methods = new ArrayList<String>();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isTabItemForPart(args[0])) {
                methods.add(String.format("%s(%s)", method.getName(), Arrays.toString(args)));
            }
            return null;
        }

        private boolean isTabItemForPart(Object obj) {
            return obj instanceof CTabItem && part.getLabel().equals(((CTabItem) obj).getText());
        }

        public int getMethodExecutionCount(String methodPattern) {
            int result = 0;
            for (String method : methods) {
                if (method.matches(methodPattern)) {
                    result++;
                }
            }
            return result;
        }
    }
}

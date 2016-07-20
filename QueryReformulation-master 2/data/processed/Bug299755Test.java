/***/
package org.eclipse.e4.ui.tests.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.junit.Test;

public class Bug299755Test {

    static class InjectionObject {

        // comment the @Inject out to get the test to pass
        @Inject
        Object object;
    }

    static class Out {

        @Inject
        private IEclipseContext context;

        public void setSelection(Object selection) {
            context.modify(IServiceConstants.ACTIVE_SELECTION, selection);
        }
    }

    static class In {

        // comment the @Inject out to get the test to pass
        @Inject
        InjectionObject object;

        private Object selection;

        @Inject
        @Optional
        void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
            this.selection = selection;
        }

        public Object getSelection() {
            return selection;
        }
    }

    @Test
    public void testBug299755() throws Exception {
        // create a top-level context
        IEclipseContext windowContext = EclipseContextFactory.create();
        windowContext.set(Object.class, new Object());
        // put the event broker inside
        windowContext.set(InjectionObject.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                return ContextInjectionFactory.make(InjectionObject.class, context);
            }
        });
        // declare selection as modifiable
        windowContext.declareModifiable(IServiceConstants.ACTIVE_SELECTION);
        // create an "out" part context
        IEclipseContext outContext = windowContext.createChild();
        // create an "in" part context
        IEclipseContext inContext = windowContext.createChild();
        Out out = ContextInjectionFactory.make(Out.class, outContext);
        In in = ContextInjectionFactory.make(In.class, inContext);
        // no selection in the beginning
        assertNull(in.getSelection());
        // change the selection
        Object selection = new Object();
        out.setSelection(selection);
        // the "in" part should've gotten the new selection
        assertEquals(selection, in.getSelection());
    }
}

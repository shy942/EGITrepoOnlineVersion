/***/
package org.eclipse.ui.internal;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class E4PartWrapper extends ViewPart {

    //$NON-NLS-1$
    public static final String E4_WRAPPER_KEY = "e4Wrapper";

    MPart wrappedPart;

    private  E4PartWrapper(MPart part) {
        wrappedPart = part;
        setPartName(part.getLabel());
    }

    public static E4PartWrapper getE4PartWrapper(MPart part) {
        if (part != null) {
            if (part.getTransientData().get(E4_WRAPPER_KEY) instanceof E4PartWrapper) {
                return (E4PartWrapper) part.getTransientData().get(E4_WRAPPER_KEY);
            }
            E4PartWrapper newWrapper = new E4PartWrapper(part);
            part.getTransientData().put(E4_WRAPPER_KEY, newWrapper);
            return newWrapper;
        }
        return null;
    }

    @Override
    public void createPartControl(Composite parent) {
    }

    @Override
    public void setFocus() {
        if (wrappedPart.getObject() != null && wrappedPart.getContext() != null)
            ContextInjectionFactory.invoke(wrappedPart.getObject(), Focus.class, wrappedPart.getContext());
    }
}

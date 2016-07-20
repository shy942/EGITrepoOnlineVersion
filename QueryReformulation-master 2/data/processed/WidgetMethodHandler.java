/***/
package org.eclipse.ui.internal.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.internal.ExceptionHandler;

/**
* Handles the cut command in both dialogs and windows. This handler is enabled
* if the focus control supports the "cut" method.
*
* @since 3.0
*/
public class WidgetMethodHandler extends AbstractHandler implements IExecutableExtension {

    /**
* The parameters to pass to the method this handler invokes. This handler
* always passes no parameters.
*/
    protected static final Class[] NO_PARAMETERS = new Class[0];

    public  WidgetMethodHandler() {
        display = Display.getCurrent();
        if (display != null) {
            focusListener = new Listener() {

                @Override
                public void handleEvent(Event event) {
                    updateEnablement();
                }
            };
            display.addFilter(SWT.FocusIn, focusListener);
        }
    }

    void updateEnablement() {
        boolean rc = isHandled();
        if (rc != isEnabled()) {
            setBaseEnabled(rc);
        }
    }

    /**
* The name of the method to be invoked by this handler. This value should
* never be <code>null</code>.
*/
    protected String methodName;

    private Listener focusListener;

    private Display display;

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final Method methodToExecute = getMethodToExecute();
        if (methodToExecute != null) {
            try {
                final Control focusControl = Display.getCurrent().getFocusControl();
                if ((focusControl instanceof Composite) && ((((Composite) focusControl).getStyle() & SWT.EMBEDDED) != 0)) {
                    /*
* Okay. Have a seat. Relax a while. This is going to be a
* bumpy ride. If it is an embedded widget, then it *might*
* be a Swing widget. At the point where this handler is
* executing, the key event is already bound to be
* swallowed. If I don't do something, then the key will be
* gone for good. So, I will try to forward the event to the
* Swing widget. Unfortunately, we can't even count on the
* Swing libraries existing, so I need to use reflection
* everywhere. And, to top it off, I need to dispatch the
* event on the Swing event queue, which means that it will
* be carried out asynchronously to the SWT event queue.
*/
                    try {
                        final Object focusComponent = getFocusComponent();
                        if (focusComponent != null) {
                            Runnable methodRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        methodToExecute.invoke(focusComponent);
                                    } catch (final IllegalAccessException e) {
                                    } catch (final InvocationTargetException e) {
                                        focusControl.getDisplay().asyncExec(new Runnable() {

                                            @Override
                                            public void run() {
                                                ExceptionHandler.getInstance().handleException(new ExecutionException("An exception occurred while executing " + methodToExecute.getName(), e.getTargetException()));
                                            }
                                        });
                                    }
                                }
                            };
                            swingInvokeLater(methodRunnable);
                        }
                    } catch (final ClassNotFoundException e) {
                    } catch (final NoSuchMethodException e) {
                        throw new Error("Something is seriously wrong here");
                    }
                } else {
                    methodToExecute.invoke(focusControl);
                }
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
                throw new ExecutionException("An exception occurred while executing " + methodToExecute.getName(), e.getTargetException());
            }
        }
        return null;
    }

    /**
* Invoke a runnable on the swing EDT.
*
* @param methodRunnable
* @throws ClassNotFoundException
* @throws NoSuchMethodException
* @throws IllegalAccessException
* @throws InvocationTargetException
*/
    protected void swingInvokeLater(Runnable methodRunnable) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final Class swingUtilitiesClass = Class.forName(//$NON-NLS-1$
        "javax.swing.SwingUtilities");
        final Method swingUtilitiesInvokeLaterMethod = swingUtilitiesClass.getMethod(//$NON-NLS-1$
        "invokeLater", new Class[] { Runnable.class });
        swingUtilitiesInvokeLaterMethod.invoke(swingUtilitiesClass, new Object[] { methodRunnable });
    }

    /**
* Find the swing focus component, if it is available.
*
* @return Hopefully, the swing focus component, but it can return
* 	<code>null</code>.
* @throws ClassNotFoundException
* @throws NoSuchMethodException
* @throws IllegalAccessException
* @throws InvocationTargetException
*/
    protected Object getFocusComponent() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        /*
* Before JRE 1.4, one has to use
* javax.swing.FocusManager.getCurrentManager().getFocusOwner(). Since
* JRE 1.4, one has to use
* java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager
* ().getFocusOwner(); the use of the older API would install a
* LegacyGlueFocusTraversalPolicy which causes endless recursions in
* some situations.
*/
        Class keyboardFocusManagerClass = null;
        try {
            keyboardFocusManagerClass = Class.forName(//$NON-NLS-1$
            "java.awt.KeyboardFocusManager");
        } catch (ClassNotFoundException e) {
        }
        if (keyboardFocusManagerClass != null) {
            // Use JRE 1.4 API
            final Method keyboardFocusManagerGetCurrentKeyboardFocusManagerMethod = keyboardFocusManagerClass.getMethod(//$NON-NLS-1$
            "getCurrentKeyboardFocusManager");
            final Object keyboardFocusManager = keyboardFocusManagerGetCurrentKeyboardFocusManagerMethod.invoke(keyboardFocusManagerClass);
            final Method keyboardFocusManagerGetFocusOwner = keyboardFocusManagerClass.getMethod(//$NON-NLS-1$
            "getFocusOwner");
            final Object focusComponent = keyboardFocusManagerGetFocusOwner.invoke(keyboardFocusManager);
            return focusComponent;
        }
        // Use JRE 1.3 API
        final Class focusManagerClass = Class.forName(//$NON-NLS-1$
        "javax.swing.FocusManager");
        final Method focusManagerGetCurrentManagerMethod = focusManagerClass.getMethod(//$NON-NLS-1$
        "getCurrentManager");
        final Object focusManager = focusManagerGetCurrentManagerMethod.invoke(focusManagerClass);
        final Method focusManagerGetFocusOwner = focusManagerClass.getMethod(//$NON-NLS-1$
        "getFocusOwner");
        final Object focusComponent = focusManagerGetFocusOwner.invoke(focusManager);
        return focusComponent;
    }

    @Override
    public final boolean isHandled() {
        return getMethodToExecute() != null;
    }

    /**
* Looks up the method on the focus control.
*
* @return The method on the focus control; <code>null</code> if none.
*/
    protected Method getMethodToExecute() {
        Display display = Display.getCurrent();
        if (display == null)
            return null;
        final Control focusControl = display.getFocusControl();
        Method method = null;
        if (focusControl != null) {
            final Class clazz = focusControl.getClass();
            try {
                method = clazz.getMethod(methodName, NO_PARAMETERS);
            } catch (NoSuchMethodException e) {
            }
        }
        if ((method == null) && (focusControl instanceof Composite) && ((((Composite) focusControl).getStyle() & SWT.EMBEDDED) != 0)) {
            /*
* We couldn't find the appropriate method on the current focus
* control. It is possible that the current focus control is an
* embedded SWT composite, which could be containing some Swing
* components. If this is the case, then we should try to pass
* through to the underlying Swing component hierarchy. Insha'allah,
* this will work.
*/
            try {
                final Object focusComponent = getFocusComponent();
                if (focusComponent != null) {
                    final Class clazz = focusComponent.getClass();
                    try {
                        method = clazz.getMethod(methodName, NO_PARAMETERS);
                    } catch (NoSuchMethodException e) {
                    }
                }
            } catch (final ClassNotFoundException e) {
            } catch (final NoSuchMethodException e) {
                throw new Error("Something is seriously wrong here");
            } catch (IllegalAccessException e) {
                throw new Error("Something is seriously wrong here");
            } catch (InvocationTargetException e) {
                throw new Error("Something is seriously wrong here");
            }
        }
        return method;
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) {
        // The data is really just a string (i.e., the method name).
        methodName = data.toString();
    }

    @Override
    public void dispose() {
        if (display != null && !display.isDisposed()) {
            display.removeFilter(SWT.FocusIn, focusListener);
        }
        display = null;
        focusListener = null;
    }
}

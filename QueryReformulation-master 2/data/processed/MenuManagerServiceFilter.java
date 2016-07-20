/***/
package org.eclipse.e4.ui.workbench.renderers.swt;

import java.lang.reflect.Method;
import javax.inject.Inject;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.swt.AbstractPartRenderer;
import org.eclipse.e4.ui.internal.workbench.swt.Policy;
import org.eclipse.e4.ui.internal.workbench.swt.WorkbenchSWTActivator;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MPopupMenu;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Widget;

public class MenuManagerServiceFilter implements Listener {

    //$NON-NLS-1$
    public static final String NUL_MENU_ITEM = "(None Applicable)";

    //$NON-NLS-1$
    private static final String TMP_ORIGINAL_CONTEXT = "MenuServiceFilter.original.context";

    private static void trace(String msg, Widget menu, MMenu menuModel) {
        WorkbenchSWTActivator.trace(Policy.MENUS, //$NON-NLS-1$ //$NON-NLS-2$
        msg + ": " + menu + ": " + menuModel, null);
    }

    private static Method aboutToShow;

    public static Method getAboutToShow() {
        if (aboutToShow == null) {
            try {
                aboutToShow = MenuManager.class.getDeclaredMethod(//$NON-NLS-1$
                "handleAboutToShow");
                aboutToShow.setAccessible(true);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return aboutToShow;
    }

    @Inject
    private Logger logger;

    @Inject
    EModelService modelService;

    @Override
    public void handleEvent(final Event event) {
        // wrap the handling in a SafeRunner so that exceptions do not prevent
        // the menu from being shown
        SafeRunner.run(new ISafeRunnable() {

            @Override
            public void handleException(Throwable e) {
                if (e instanceof Error) {
                    // errors are deadly, we shouldn't ignore these
                    throw (Error) e;
                } else {
                    // log exceptions otherwise
                    if (logger != null) {
                        logger.error(e);
                    }
                }
            }

            @Override
            public void run() throws Exception {
                safeHandleEvent(event);
            }
        });
    }

    private void safeHandleEvent(Event event) {
        if (!(event.widget instanceof Menu)) {
            return;
        }
        final Menu menu = (Menu) event.widget;
        if (event.type == SWT.Dispose) {
            //$NON-NLS-1$
            trace("handleMenu.Dispose", menu, null);
            cleanUp(menu);
        }
        Object obj = menu.getData(AbstractPartRenderer.OWNING_ME);
        if (obj == null && menu.getParentItem() != null) {
            obj = menu.getParentItem().getData(AbstractPartRenderer.OWNING_ME);
        }
        if (obj instanceof MPopupMenu) {
            handleContextMenu(event, menu, (MPopupMenu) obj);
        } else if (obj instanceof MMenu) {
            handleMenu(event, menu, (MMenu) obj);
        }
    }

    private void handleMenu(final Event event, final Menu menu, final MMenu menuModel) {
        if ((menu.getStyle() & SWT.BAR) != 0) {
            // don't process the menu bar, it's not fair :-)
            return;
        }
        switch(event.type) {
            case SWT.Show:
                cleanUp(menu);
                showMenu(event, menu, menuModel);
                break;
            case SWT.Hide:
                // TODO we'll clean up on show
                break;
        }
    }

    public void showMenu(final Event event, final Menu menu, final MMenu menuModel) {
    // System.err.println("showMenu: " + menuModel + "\n\t" + menu);
    }

    private void handleContextMenu(final Event event, final Menu menu, final MPopupMenu menuModel) {
        switch(event.type) {
            case SWT.Show:
                cleanUp(menu);
                showPopup(event, menu, menuModel);
                break;
            case SWT.Hide:
                hidePopup(event, menu, menuModel);
                break;
        }
    }

    public void hidePopup(Event event, Menu menu, MPopupMenu menuModel) {
        // System.err.println("hidePopup: " + menuModel + "\n\t" + menu);
        final IEclipseContext popupContext = menuModel.getContext();
        final IEclipseContext originalChild = (IEclipseContext) popupContext.get(TMP_ORIGINAL_CONTEXT);
        popupContext.remove(TMP_ORIGINAL_CONTEXT);
        if (!menu.isDisposed()) {
            menu.getDisplay().asyncExec(new Runnable() {

                @Override
                public void run() {
                    if (originalChild == null) {
                        popupContext.deactivate();
                    } else {
                        originalChild.activate();
                    }
                }
            });
        }
    }

    public void showPopup(final Event event, final Menu menu, final MPopupMenu menuModel) {
        // System.err.println("showPopup: " + menuModel + "\n\t" + menu);
        // we need some context foolery here
        final IEclipseContext popupContext = menuModel.getContext();
        final IEclipseContext parentContext = popupContext.getParent();
        final IEclipseContext originalChild = parentContext.getActiveChild();
        popupContext.activate();
        popupContext.set(TMP_ORIGINAL_CONTEXT, originalChild);
    }

    public void cleanUp(final Menu menu) {
    // System.err.println("cleanUp: " + menu);
    }

    public void dispose() {
    // System.err.println("dispose");
    }
}
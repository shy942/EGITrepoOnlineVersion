/***/
package org.eclipse.ui.internal.ide.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.dialogs.OpenResourceDialog;

/**
* Implements the open resource action. Opens a dialog prompting for a file and
* opens the selected file in an editor.
*
* @since 2.1
*/
public final class OpenResourceHandler extends Action implements IHandler, IWorkbenchWindowActionDelegate {

    /**
* The identifier of the parameter storing the file path.
*/
    //$NON-NLS-1$
    private static final String PARAM_ID_FILE_PATH = "filePath";

    /**
* A collection of objects listening to changes to this manager. This
* collection is <code>null</code> if there are no listeners.
*/
    private transient ListenerList listenerList = null;

    /**
* Creates a new instance of the class.
*/
    public  OpenResourceHandler() {
        super();
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IIDEHelpContextIds.OPEN_WORKSPACE_FILE_ACTION);
    }

    @Override
    public final void addHandlerListener(final IHandlerListener listener) {
        if (listenerList == null) {
            listenerList = new ListenerList(ListenerList.IDENTITY);
        }
        listenerList.add(listener);
    }

    @Override
    public final void dispose() {
        listenerList = null;
    }

    @Override
    public final Object execute(final ExecutionEvent event) throws ExecutionException {
        final List files = new ArrayList();
        if (event.getParameter(PARAM_ID_FILE_PATH) == null) {
            // Prompt the user for the resource to open.
            Object[] result = queryFileResource();
            if (result != null) {
                for (int i = 0; i < result.length; i++) {
                    if (result[i] instanceof IFile) {
                        files.add(result[i]);
                    }
                }
            }
        } else {
            // Use the given parameter.
            final IResource resource = (IResource) event.getObjectParameterForExecution(PARAM_ID_FILE_PATH);
            if (!(resource instanceof IFile)) {
                throw new ExecutionException(//$NON-NLS-1$
                "filePath parameter must identify a file");
            }
            files.add(resource);
        }
        if (files.size() > 0) {
            final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window == null) {
                //$NON-NLS-1$
                throw new ExecutionException("no active workbench window");
            }
            final IWorkbenchPage page = window.getActivePage();
            if (page == null) {
                //$NON-NLS-1$
                throw new ExecutionException("no active workbench page");
            }
            try {
                for (Iterator it = files.iterator(); it.hasNext(); ) {
                    IDE.openEditor(page, (IFile) it.next(), true);
                }
            } catch (final PartInitException e) {
                throw new ExecutionException("error opening file in editor", e);
            }
        }
        return null;
    }

    @Override
    public final void init(final IWorkbenchWindow window) {
    // Do nothing.
    }

    /**
* Query the user for the resources that should be opened
*
* @return the resource that should be opened.
*/
    private final Object[] queryFileResource() {
        final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window == null) {
            return null;
        }
        final Shell parent = window.getShell();
        final IContainer input = ResourcesPlugin.getWorkspace().getRoot();
        final OpenResourceDialog dialog = new OpenResourceDialog(parent, input, IResource.FILE);
        final int resultCode = dialog.open();
        if (resultCode != Window.OK) {
            return null;
        }
        final Object[] result = dialog.getResult();
        return result;
    }

    @Override
    public final void removeHandlerListener(final IHandlerListener listener) {
        if (listenerList != null) {
            listenerList.remove(listener);
            if (listenerList.isEmpty()) {
                listenerList = null;
            }
        }
    }

    @Override
    public final void run(final IAction action) {
        try {
            execute(new ExecutionEvent());
        } catch (final ExecutionException e) {
        }
    }

    @Override
    public final void selectionChanged(final IAction action, final ISelection selection) {
    // Do nothing.
    }
}

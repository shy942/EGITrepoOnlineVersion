/***/
package org.eclipse.ui.internal.ide.actions;

import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
* Standard action for opening an editor on local file(s).
* <p>
* This class may be instantiated; it is not intended to be subclassed.
* </p>
*/
public class OpenLocalFileAction extends Action implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow window;

    private String filterPath;

    /**
* Creates a new action for opening a local file.
*/
    public  OpenLocalFileAction() {
        setEnabled(true);
    }

    @Override
    public void dispose() {
        window = null;
        filterPath = null;
    }

    @Override
    public void init(IWorkbenchWindow window) {
        this.window = window;
        //$NON-NLS-1$
        filterPath = System.getProperty("user.home");
    }

    @Override
    public void run(IAction action) {
        run();
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }

    @Override
    public void run() {
        FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN | SWT.MULTI);
        dialog.setText(IDEWorkbenchMessages.OpenLocalFileAction_title);
        dialog.setFilterPath(filterPath);
        dialog.open();
        String[] names = dialog.getFileNames();
        if (names != null) {
            filterPath = dialog.getFilterPath();
            int numberOfFilesNotFound = 0;
            StringBuffer notFound = new StringBuffer();
            for (int i = 0; i < names.length; i++) {
                IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(filterPath));
                fileStore = fileStore.getChild(names[i]);
                IFileInfo fetchInfo = fileStore.fetchInfo();
                if (!fetchInfo.isDirectory() && fetchInfo.exists()) {
                    IWorkbenchPage page = window.getActivePage();
                    try {
                        IDE.openEditorOnFileStore(page, fileStore);
                    } catch (PartInitException e) {
                        String msg = NLS.bind(IDEWorkbenchMessages.OpenLocalFileAction_message_errorOnOpen, fileStore.getName());
                        IDEWorkbenchPlugin.log(msg, e.getStatus());
                        MessageDialog.open(MessageDialog.ERROR, window.getShell(), IDEWorkbenchMessages.OpenLocalFileAction_title, msg, SWT.SHEET);
                    }
                } else {
                    if (++numberOfFilesNotFound > 1)
                        notFound.append('\n');
                    notFound.append(fileStore.getName());
                }
            }
            if (numberOfFilesNotFound > 0) {
                String msgFmt = numberOfFilesNotFound == 1 ? IDEWorkbenchMessages.OpenLocalFileAction_message_fileNotFound : IDEWorkbenchMessages.OpenLocalFileAction_message_filesNotFound;
                String msg = NLS.bind(msgFmt, notFound.toString());
                MessageDialog.open(MessageDialog.ERROR, window.getShell(), IDEWorkbenchMessages.OpenLocalFileAction_title, msg, SWT.SHEET);
            }
        }
    }
}

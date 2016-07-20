/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

/**
* A dynamic menu item which supports to switch to other Windows.
*/
public class ReopenEditorMenu extends ContributionItem {

    private IWorkbenchWindow window;

    private EditorHistory history;

    private boolean showSeparator;

    // the maximum length for a file name; must be >= 4
    private static final int MAX_TEXT_LENGTH = 40;

    // only assign mnemonic to the first nine items
    private static final int MAX_MNEMONIC_SIZE = 9;

    /**
* Create a new instance.
* @param window the window on which the menu is to be created
* @param id menu's id
* @param showSeparator whether or not to show a separator
*/
    public  ReopenEditorMenu(IWorkbenchWindow window, String id, boolean showSeparator) {
        super(id);
        this.window = window;
        this.showSeparator = showSeparator;
        IWorkbench workbench = window.getWorkbench();
        if (workbench instanceof Workbench) {
            history = ((Workbench) workbench).getEditorHistory();
        }
    }

    /**
* Returns the text for a history item.  This may be truncated to fit
* within the MAX_TEXT_LENGTH.
*/
    private String calcText(int index, EditorHistoryItem item) {
        // consult its memento if it is not restored yet.
        return calcText(index, item.getName(), item.getToolTipText(), Window.getDefaultOrientation() == SWT.RIGHT_TO_LEFT);
    }

    /**
* Return a string suitable for a file MRU list.  This should not be called
* outside the framework.
*
* @param index the index in the MRU list
* @param name the file name
* @param toolTip potentially the path
* @param rtl should it be right-to-left
* @return a string suitable for an MRU file menu
*/
    public static String calcText(int index, String name, String toolTip, boolean rtl) {
        StringBuffer sb = new StringBuffer();
        int mnemonic = index + 1;
        StringBuffer nm = new StringBuffer();
        nm.append(mnemonic);
        if (mnemonic <= MAX_MNEMONIC_SIZE) {
            //$NON-NLS-1$
            nm.insert(nm.length() - (mnemonic + "").length(), '&');
        }
        //        sb.append(" "); //$NON-NLS-1$
        String fileName = name;
        String pathName = toolTip;
        if (pathName.equals(fileName)) {
            // tool tip text isn't necessarily a path;
            // sometimes it's the same as name, so it shouldn't be treated as a path then
            //$NON-NLS-1$
            pathName = "";
        }
        IPath path = new Path(pathName);
        // if last segment in path is the fileName, remove it
        if (path.segmentCount() > 1 && path.segment(path.segmentCount() - 1).equals(fileName)) {
            path = path.removeLastSegments(1);
            pathName = path.toString();
        }
        if ((fileName.length() + pathName.length()) <= (MAX_TEXT_LENGTH - 4)) {
            // entire item name fits within maximum length
            sb.append(fileName);
            if (pathName.length() > 0) {
                //$NON-NLS-1$
                sb.append("  [");
                sb.append(pathName);
                //$NON-NLS-1$
                sb.append("]");
            }
        } else {
            // need to shorten the item name
            int length = fileName.length();
            if (length > MAX_TEXT_LENGTH) {
                // file name does not fit within length, truncate it
                sb.append(fileName.substring(0, MAX_TEXT_LENGTH - 3));
                //$NON-NLS-1$
                sb.append("...");
            } else if (length > MAX_TEXT_LENGTH - 7) {
                sb.append(fileName);
            } else {
                sb.append(fileName);
                int segmentCount = path.segmentCount();
                if (segmentCount > 0) {
                    // 7 chars are taken for "  [...]"
                    length += 7;
                    //$NON-NLS-1$
                    sb.append("  [");
                    // Add first n segments that fit
                    int i = 0;
                    while (i < segmentCount && length < MAX_TEXT_LENGTH) {
                        String segment = path.segment(i);
                        if (length + segment.length() < MAX_TEXT_LENGTH) {
                            sb.append(segment);
                            sb.append(IPath.SEPARATOR);
                            length += segment.length() + 1;
                            i++;
                        } else if (i == 0) {
                            // append at least part of the first segment
                            sb.append(segment.substring(0, MAX_TEXT_LENGTH - length));
                            length = MAX_TEXT_LENGTH;
                            break;
                        } else {
                            break;
                        }
                    }
                    //$NON-NLS-1$
                    sb.append("...");
                    i = segmentCount - 1;
                    // Add last n segments that fit
                    while (i > 0 && length < MAX_TEXT_LENGTH) {
                        String segment = path.segment(i);
                        if (length + segment.length() < MAX_TEXT_LENGTH) {
                            sb.append(IPath.SEPARATOR);
                            sb.append(segment);
                            length += segment.length() + 1;
                            i--;
                        } else {
                            break;
                        }
                    }
                    //$NON-NLS-1$
                    sb.append("]");
                }
            }
        }
        final String process;
        if (rtl) {
            //$NON-NLS-1$
            process = sb + " " + nm;
        } else {
            //$NON-NLS-1$
            process = nm + " " + sb;
        }
        //$NON-NLS-1$
        return TextProcessor.process(process, TextProcessor.getDefaultDelimiters() + "[]");
    }

    /**
* Fills the given menu with
* menu items for all windows.
*/
    @Override
    public void fill(final Menu menu, int index) {
        if (window.getActivePage() == null || window.getActivePage().getPerspective() == null) {
            return;
        }
        int itemsToShow = WorkbenchPlugin.getDefault().getPreferenceStore().getInt(IPreferenceConstants.RECENT_FILES);
        if (itemsToShow == 0 || history == null) {
            return;
        }
        // Get items.
        EditorHistoryItem[] historyItems = history.getItems();
        int n = Math.min(itemsToShow, historyItems.length);
        if (n <= 0) {
            return;
        }
        if (showSeparator) {
            new MenuItem(menu, SWT.SEPARATOR, index);
            ++index;
        }
        final int menuIndex[] = new int[] { index };
        for (int i = 0; i < n; i++) {
            final EditorHistoryItem item = historyItems[i];
            final int historyIndex = i;
            SafeRunner.run(new SafeRunnable() {

                @Override
                public void run() throws Exception {
                    String text = calcText(historyIndex, item);
                    MenuItem mi = new MenuItem(menu, SWT.PUSH, menuIndex[0]);
                    ++menuIndex[0];
                    mi.setText(text);
                    mi.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            open(item);
                        }
                    });
                }

                @Override
                public void handleException(Throwable e) {
                    // just skip the item if there's an error,
                    // e.g. in the calculation of the shortened name
                    //$NON-NLS-1$
                    WorkbenchPlugin.log(getClass(), "fill", e);
                }
            });
        }
    }

    /**
* Overridden to always return true and force dynamic menu building.
*/
    @Override
    public boolean isDynamic() {
        return true;
    }

    /**
* Reopens the editor for the given history item.
*/
    private void open(EditorHistoryItem item) {
        IWorkbenchPage page = window.getActivePage();
        if (page != null) {
            try {
                String itemName = item.getName();
                if (!item.isRestored()) {
                    item.restoreState();
                }
                IEditorInput input = item.getInput();
                IEditorDescriptor desc = item.getDescriptor();
                if (input == null || !input.exists() || desc == null) {
                    String title = WorkbenchMessages.OpenRecent_errorTitle;
                    String msg = NLS.bind(WorkbenchMessages.OpenRecent_unableToOpen, itemName);
                    MessageDialog.openWarning(window.getShell(), title, msg);
                    history.remove(item);
                } else {
                    page.openEditor(input, desc.getId());
                }
            } catch (PartInitException e2) {
                String title = WorkbenchMessages.OpenRecent_errorTitle;
                MessageDialog.openWarning(window.getShell(), title, e2.getMessage());
                history.remove(item);
            }
        }
    }
}

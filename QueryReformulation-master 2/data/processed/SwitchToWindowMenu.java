/***/
package org.eclipse.ui.internal;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

/**
* A dynamic menu item to switch to other opened workbench windows.
*/
public class SwitchToWindowMenu extends ContributionItem {

    private static final int MAX_TEXT_LENGTH = 40;

    private IWorkbenchWindow workbenchWindow;

    private boolean showSeparator;

    /**
* Creates a new instance of this class.
*
* @param window the workbench window this action applies to
* @param showSeparator whether to add a separator in the menu
*/
    public  SwitchToWindowMenu(IWorkbenchWindow window, String id, boolean showSeparator) {
        super(id);
        this.workbenchWindow = window;
        this.showSeparator = showSeparator;
    }

    /**
* Returns the text for a window. This may be truncated to fit
* within the MAX_TEXT_LENGTH.
*/
    private String calcText(int number, IWorkbenchWindow window) {
        String suffix = window.getShell().getText();
        if (suffix == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        if (number < 10) {
            sb.append('&');
        }
        sb.append(number);
        sb.append(' ');
        if (suffix.length() <= MAX_TEXT_LENGTH) {
            sb.append(suffix);
        } else {
            sb.append(suffix.substring(0, MAX_TEXT_LENGTH));
            //$NON-NLS-1$
            sb.append("...");
        }
        return sb.toString();
    }

    /**
* Fills the given menu with menu items for all
* opened workbench windows.
*/
    @Override
    public void fill(Menu menu, int index) {
        // Get workbench windows.
        IWorkbench workbench = workbenchWindow.getWorkbench();
        IWorkbenchWindow[] array = workbench.getWorkbenchWindows();
        // avoid showing the separator and list for 0 or 1 items
        if (array.length < 2) {
            return;
        }
        // Add separator.
        if (showSeparator) {
            new MenuItem(menu, SWT.SEPARATOR, index);
            ++index;
        }
        // Add one item for each window.
        int count = 1;
        for (int i = 0; i < array.length; i++) {
            final IWorkbenchWindow window = array[i];
            // can encounter disposed shells if this update is in response to a shell closing
            if (!window.getShell().isDisposed()) {
                String name = calcText(count, window);
                if (name != null) {
                    MenuItem mi = new MenuItem(menu, SWT.RADIO, index);
                    index++;
                    count++;
                    mi.setText(name);
                    mi.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            Shell windowShell = window.getShell();
                            if (windowShell.getMinimized()) {
                                windowShell.setMinimized(false);
                            }
                            windowShell.setActive();
                            windowShell.moveAbove(null);
                        }
                    });
                    mi.setSelection(window == workbenchWindow);
                }
            }
        }
    }

    /**
* Overridden to always return true and force dynamic menu building.
*/
    @Override
    public boolean isDirty() {
        return true;
    }

    /**
* Overridden to always return true and force dynamic menu building.
*/
    @Override
    public boolean isDynamic() {
        return true;
    }
}

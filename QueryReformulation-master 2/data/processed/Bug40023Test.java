/***/
package org.eclipse.ui.tests.keys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.keys.BindingService;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests Bug 40023
*
* @since 3.0
*/
public class Bug40023Test extends UITestCase {

    /**
* Retrieves a menu item matching or starting with the given name from an
* array of menu items.
*
* @param menuItems
*            The array of menu items to search; must not be <code>null</code>
* @param text
*            The text to look for; may be <code>null</code>.
* @return The menu item, if any is found; <code>null</code> otherwise.
*/
    public static MenuItem getMenuItem(MenuItem[] menuItems, String text) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getText().startsWith(text)) {
                return menuItem;
            }
        }
        return null;
    }

    /**
* Constructor for Bug40023Test.
*
* @param name
*            The name of the test
*/
    public  Bug40023Test(String name) {
        super(name);
    }

    /**
* Tests that check box items on the menu are checked when activated from
* the keyboard.
*
* @throws CommandException
*             If execution of the handler fails.
* @throws CoreException
*             If the exported preferences file is invalid for some reason.
* @throws FileNotFoundException
*             If the temporary file is removed before it can be read in.
*             (Wow)
* @throws IOException
*             If the creation of or the writing to the temporary file
*             fails for some reason.
* @throws ParseException
*             If the key binding cannot be parsed.
*/
    public void testCheckOnCheckbox() throws CoreException, CommandException, FileNotFoundException, IOException, ParseException {
        // Open a window to run the test.
        IWorkbenchWindow window = openTestWindow();
        Workbench workbench = (Workbench) window.getWorkbench();
        // Set up a key binding for "Lock Toolbars".
        //$NON-NLS-1$
        String commandId = "org.eclipse.ui.window.lockToolBar";
        //$NON-NLS-1$
        String keySequenceText = "CTRL+ALT+L";
        PreferenceMutator.setKeyBinding(commandId, keySequenceText);
        // Press "CTRL+ALT+L" to lock the toolbars.
        List keyStrokes = new ArrayList();
        keyStrokes.add(KeyStroke.getInstance(keySequenceText));
        Event event = new Event();
        BindingService support = (BindingService) workbench.getAdapter(IBindingService.class);
        support.getKeyboard().press(keyStrokes, event);
        // Check that the "Lock Toolbars" menu item is now checked.
        Shell shell = window.getShell();
        MenuItem windowMenu = getMenuItem(shell.getMenuBar().getItems(), //$NON-NLS-1$
        "&Window");
        MenuItem lockToolBarsMenuItem = getMenuItem(windowMenu.getMenu().getItems(), //$NON-NLS-1$
        "Lock the &Toolbars");
        assertTrue("Checkbox menu item is not checked.", //$NON-NLS-1$
        lockToolBarsMenuItem.getSelection());
    }
}

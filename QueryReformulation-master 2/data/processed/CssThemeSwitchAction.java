/***/
package org.eclipse.e4.demo.cssbridge.ui.actions;

import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

public class CssThemeSwitchAction extends Action {

    private String themeId;

    private IThemeEngine themeEngine;

    private IWorkbenchWindow window;

    public  CssThemeSwitchAction(String actionId, String text, String iconPath, String themeId, IWorkbenchWindow window) {
        super(text);
        setId(actionId);
        setActionDefinitionId(actionId);
        setImageDescriptor(org.eclipse.e4.demo.cssbridge.ui.Activator.getImageDescriptor(iconPath));
        this.window = window;
        this.themeId = themeId;
        themeEngine = window.getService(IThemeEngine.class);
    }

    @Override
    public void run() {
        if (themeEngine == null) {
            MessageDialog.openWarning(window.getShell(), "Warnings", "The CSS ThemeEngine is not registered");
            return;
        }
        ITheme theme = themeEngine.getActiveTheme();
        if (theme != null && !theme.getId().equals(themeId)) {
            themeEngine.setTheme(themeId, false);
        }
    }
}

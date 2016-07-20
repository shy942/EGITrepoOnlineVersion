/***/
package org.eclipse.ui.preferences;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.internal.WorkbenchMessages;

/**
* The ViewPreferencesAction is the action for opening
* a view preferences dialog on a class.
*
* @since 3.1
*/
public abstract class ViewPreferencesAction extends Action {

    /**
* Create a new instance of the receiver.
*/
    public  ViewPreferencesAction() {
        super(WorkbenchMessages.OpenPreferences_text);
    }

    @Override
    public void run() {
        openViewPreferencesDialog();
    }

    /**
* Open a view preferences dialog for the receiver.
*/
    public abstract void openViewPreferencesDialog();
}

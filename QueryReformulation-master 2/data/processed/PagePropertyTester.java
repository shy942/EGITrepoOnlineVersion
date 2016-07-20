/***/
package org.eclipse.ui.internal.ide.handlers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.dialogs.FilteredPreferenceDialog;

/**
* PropertyTester which is able to test whether a FilteredPreferenceDialog on a
* given shell shows a certain {@link IPreferencePage} by comparing the classes'
* name to an expectedValue.
*
* @since 3.4
*
*/
public class PagePropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof Shell) {
            Shell shell = (Shell) receiver;
            if (shell.isDisposed()) {
                return false;
            }
            Object shellData = shell.getData();
            if (shellData instanceof FilteredPreferenceDialog) {
                FilteredPreferenceDialog propertyDialog = (FilteredPreferenceDialog) shellData;
                IPreferencePage currentPage = propertyDialog.getCurrentPage();
                if (currentPage != null) {
                    return currentPage.getClass().getName().equals(expectedValue);
                }
            }
        }
        return false;
    }
}

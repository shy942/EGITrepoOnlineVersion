/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.preferences.WorkbenchPreferenceExpressionNode;

/**
* This class is created to avoid mentioning preferences
* in this context. Ideally, JFace preference classes should be
* renamed into something more generic (for example,
* 'TreeNavigationDialog').
*/
public class PropertyPageManager extends PreferenceManager {

    /**
* The constructor.
*/
    public  PropertyPageManager() {
        super(WorkbenchPlugin.PREFERENCE_PAGE_CATEGORY_SEPARATOR, //$NON-NLS-1$
        new WorkbenchPreferenceExpressionNode(""));
    }
}

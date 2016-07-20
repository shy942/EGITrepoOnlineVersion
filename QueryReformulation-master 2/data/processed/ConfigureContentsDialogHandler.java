/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.views.markers.MarkerViewHandler;

/**
* The ConfigureContentsDialogHandler is the handler for opening the contents
* configuration dialog
*
* @since 3.4
*
*/
public class ConfigureContentsDialogHandler extends MarkerViewHandler implements IHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        ExtendedMarkersView view = getView(event);
        if (view == null)
            return this;
        view.openFiltersDialog();
        return this;
    }
}

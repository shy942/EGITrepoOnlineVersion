/***/
package org.eclipse.ui.internal.handlers;

import java.util.List;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimBar;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
* Handler which allows to hide all trimbars. It adds a tag to the corresponding
* window. If triggered again, it restores the original visibility of the
* trimbars.
*/
public class HideTrimBarsHandler extends AbstractHandler {

    /**
*
*/
    //$NON-NLS-1$
    private static final String INITIAL_TRIM_VISIBILIY = "initialTrimVisibilityValue";

    //$NON-NLS-1$
    private static final String WINDOWS_WITH_MINIMIZED_TRIMBARS = "windowsWithMinimizedTrimbars";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        MTrimmedWindow winModel = window.getService(MTrimmedWindow.class);
        EModelService modelService = window.getService(EModelService.class);
        // ensure we have everything we need
        if ((winModel == null || modelService == null)) {
            return null;
        }
        if (winModel.getTags().contains(WINDOWS_WITH_MINIMIZED_TRIMBARS)) {
            winModel.getTags().remove(WINDOWS_WITH_MINIMIZED_TRIMBARS);
            disableCodeFocus(winModel, modelService);
        } else {
            enableCodeFocus(winModel, modelService);
            winModel.getTags().add(WINDOWS_WITH_MINIMIZED_TRIMBARS);
        }
        return null;
    }

    private void disableCodeFocus(MTrimmedWindow window, EModelService modelService) {
        List<MTrimBar> tcList = modelService.findElements(window, null, MTrimBar.class, null);
        for (MTrimBar tc : tcList) {
            boolean visible = true;
            String initialTrimVisibility = tc.getPersistedState().get(INITIAL_TRIM_VISIBILIY);
            if (initialTrimVisibility != null && !initialTrimVisibility.isEmpty()) {
                visible = Boolean.parseBoolean(initialTrimVisibility);
                tc.getPersistedState().remove(INITIAL_TRIM_VISIBILIY);
            }
            tc.setVisible(visible);
        }
    }

    private void enableCodeFocus(MTrimmedWindow window, EModelService modelService) {
        List<MTrimBar> tcList = modelService.findElements(window, null, MTrimBar.class, null);
        for (MTrimBar tc : tcList) {
            // remember the visibility state in case some trimbars are already
            // not visible
            tc.getPersistedState().put(INITIAL_TRIM_VISIBILIY, String.valueOf(tc.isVisible()));
            tc.setVisible(false);
        }
    }
}
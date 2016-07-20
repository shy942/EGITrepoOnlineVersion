/***/
package org.eclipse.ui.internal.e4.compatibility;

import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.ui.IViewLayout;

public class ModeledViewLayout implements IViewLayout {

    private MUIElement viewME;

    public  ModeledViewLayout(MPart view) {
        viewME = view;
    }

    public  ModeledViewLayout(MPlaceholder placeholder) {
        viewME = placeholder;
    }

    @Override
    public boolean getShowTitle() {
        return !viewME.getTags().contains(IPresentationEngine.NO_TITLE);
    }

    @Override
    public boolean isCloseable() {
        return !viewME.getTags().contains(IPresentationEngine.NO_CLOSE);
    }

    @Override
    public boolean isMoveable() {
        return !viewME.getTags().contains(IPresentationEngine.NO_MOVE);
    }

    @Override
    public boolean isStandalone() {
        MUIElement parentElement = viewME.getParent();
        return !(parentElement instanceof MPartStack);
    }

    @Override
    public void setCloseable(boolean closeable) {
        if (closeable)
            viewME.getTags().remove(IPresentationEngine.NO_CLOSE);
        else
            viewME.getTags().add(IPresentationEngine.NO_CLOSE);
    }

    @Override
    public void setMoveable(boolean moveable) {
        if (moveable)
            viewME.getTags().remove(IPresentationEngine.NO_MOVE);
        else
            viewME.getTags().add(IPresentationEngine.NO_MOVE);
    }
}

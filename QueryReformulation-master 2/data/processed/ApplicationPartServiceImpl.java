/***/
package org.eclipse.e4.ui.internal.workbench;

import java.util.Collection;
import java.util.Optional;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;

public class ApplicationPartServiceImpl implements EPartService {

    private MApplication application;

    @Inject
     ApplicationPartServiceImpl(MApplication application) {
        this.application = application;
    }

    private EPartService getActiveWindowService() {
        IEclipseContext activeWindowContext = application.getContext().getActiveChild();
        if (activeWindowContext == null) {
            //$NON-NLS-1$
            throw new IllegalStateException("Application does not have an active window");
        }
        EPartService activeWindowPartService = activeWindowContext.get(EPartService.class);
        if (activeWindowPartService == null) {
            //$NON-NLS-1$
            throw new IllegalStateException("Active window context is invalid");
        }
        if (activeWindowPartService == this) {
            //$NON-NLS-1$
            throw new IllegalStateException("Application does not have an active window");
        }
        return activeWindowPartService;
    }

    @Override
    public void addPartListener(IPartListener listener) {
        throw new UnsupportedOperationException(//$NON-NLS-1$
        "Listeners should only be attached/removed from a window's part service");
    }

    @Override
    public void removePartListener(IPartListener listener) {
        throw new UnsupportedOperationException(//$NON-NLS-1$
        "Listeners should only be attached/removed from a window's part service");
    }

    @Override
    public boolean isPartOrPlaceholderInPerspective(String elementId, MPerspective perspective) {
        return getActiveWindowService().isPartOrPlaceholderInPerspective(elementId, perspective);
    }

    @Override
    public void switchPerspective(MPerspective perspective) {
        getActiveWindowService().switchPerspective(perspective);
    }

    @Override
    public Optional<MPerspective> switchPerspective(String perspectiveId) {
        return getActiveWindowService().switchPerspective(perspectiveId);
    }

    @Override
    public void activate(MPart part) {
        getActiveWindowService().activate(part);
    }

    @Override
    public void activate(MPart part, boolean requiresFocus) {
        getActiveWindowService().activate(part, requiresFocus);
    }

    @Override
    public void requestActivation() {
        getActiveWindowService().requestActivation();
    }

    @Override
    public void bringToTop(MPart part) {
        getActiveWindowService().bringToTop(part);
    }

    @Override
    public MPart findPart(String id) {
        return getActiveWindowService().findPart(id);
    }

    @Override
    public Collection<MPart> getParts() {
        return getActiveWindowService().getParts();
    }

    @Override
    public MPart getActivePart() {
        return getActiveWindowService().getActivePart();
    }

    @Override
    public boolean isPartVisible(MPart part) {
        return getActiveWindowService().isPartVisible(part);
    }

    @Override
    public MPart createPart(String id) {
        return getActiveWindowService().createPart(id);
    }

    @Override
    public MPlaceholder createSharedPart(String id) {
        return getActiveWindowService().createSharedPart(id);
    }

    @Override
    public MPlaceholder createSharedPart(String id, boolean force) {
        return getActiveWindowService().createSharedPart(id, force);
    }

    @Override
    public MPart showPart(String id, PartState partState) {
        return getActiveWindowService().showPart(id, partState);
    }

    @Override
    public MPart showPart(MPart part, PartState partState) {
        return getActiveWindowService().showPart(part, partState);
    }

    @Override
    public void hidePart(MPart part) {
        getActiveWindowService().hidePart(part);
    }

    @Override
    public void hidePart(MPart part, boolean force) {
        getActiveWindowService().hidePart(part, force);
    }

    @Override
    public Collection<MPart> getDirtyParts() {
        return getActiveWindowService().getDirtyParts();
    }

    @Override
    public boolean savePart(MPart part, boolean confirm) {
        return getActiveWindowService().savePart(part, confirm);
    }

    @Override
    public boolean saveAll(boolean confirm) {
        return getActiveWindowService().saveAll(confirm);
    }

    @Override
    public Collection<MInputPart> getInputParts(String inputUri) {
        return getActiveWindowService().getInputParts(inputUri);
    }
}

/***/
package org.eclipse.e4.ui.workbench.addons.dndaddon;

import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.UIEvents.EventTags;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.osgi.service.event.Event;

/**
* Addon supporting standard drag and drop management
*/
public class DnDAddon {

    @Inject
    @Optional
    void subscribeTopicWidget(@UIEventTopic(UIEvents.UIElement.TOPIC_WIDGET) Event event) {
        MUIElement changedElement = (MUIElement) event.getProperty(EventTags.ELEMENT);
        if (!(changedElement instanceof MWindow))
            return;
        Widget widget = (Widget) event.getProperty(EventTags.NEW_VALUE);
        if (widget instanceof Shell && !widget.isDisposed()) {
            //$NON-NLS-1$
            DnDManager theManager = (DnDManager) widget.getData("DnDManager");
            if (theManager == null) {
                theManager = new DnDManager((MWindow) changedElement);
                //$NON-NLS-1$
                widget.setData("DnDManager", theManager);
            }
        }
    }
}

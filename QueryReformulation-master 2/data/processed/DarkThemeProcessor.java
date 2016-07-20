/***/
package org.eclipse.e4.ui.swt.internal.gtk;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.swt.internal.gtk.OS;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.event.EventHandler;

@SuppressWarnings("restriction")
public class DarkThemeProcessor {

    @Inject
    IEventBroker eventBroker;

    private EventHandler eventHandler;

    @PostConstruct
    public void intialize() {
        eventHandler =  event -> {
            if (event == null) {
                return;
            }
            ITheme theme = (ITheme) event.getProperty("theme");
            final boolean isDark = theme.getId().contains("dark");
            Display display = (Display) event.getProperty(IThemeEngine.Events.DEVICE);
            display.asyncExec(() -> OS.setDarkThemePreferred(isDark));
        };
        // using the IEventBroker explicitly because the @EventTopic annotation
        // is unpredictable with processors within the debugger
        eventBroker.subscribe(IThemeEngine.Events.THEME_CHANGED, eventHandler);
    }

    @PreDestroy
    public void cleanUp() {
        eventBroker.unsubscribe(eventHandler);
    }
}

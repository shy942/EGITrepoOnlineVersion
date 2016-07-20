/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

/**
* @since 3.4
*
*/
public class WidgetListenerUtil {

    /**
* @param widget
* @param event
* @param listener
*/
    public static void asyncAddListener(final Widget widget, final int event, final Listener listener) {
        if (widget == null)
            return;
        if (widget.isDisposed())
            return;
        Display display = widget.getDisplay();
        if (display == Display.getCurrent()) {
            widget.addListener(event, listener);
        } else {
            DisplayRealm.getRealm(display).exec(new Runnable() {

                @Override
                public void run() {
                    if (!widget.isDisposed())
                        widget.addListener(event, listener);
                }
            });
        }
    }

    /**
* @param widget
* @param event
* @param listener
*/
    public static void asyncRemoveListener(final Widget widget, final int event, final Listener listener) {
        if (widget == null)
            return;
        if (widget.isDisposed())
            return;
        Display display = widget.getDisplay();
        if (display == Display.getCurrent()) {
            widget.removeListener(event, listener);
        } else {
            DisplayRealm.getRealm(display).exec(new Runnable() {

                @Override
                public void run() {
                    if (!widget.isDisposed())
                        widget.removeListener(event, listener);
                }
            });
        }
    }
}

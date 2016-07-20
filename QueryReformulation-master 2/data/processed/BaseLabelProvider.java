/***/
package org.eclipse.jface.viewers;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.jface.util.SafeRunnable;

/**
* BaseLabelProvider is a default concrete implementation of
* {@link IBaseLabelProvider}
*
* @since 3.3
*
*/
public class BaseLabelProvider extends EventManager implements IBaseLabelProvider {

    @Override
    public void addListener(ILabelProviderListener listener) {
        addListenerObject(listener);
    }

    /**
* The <code>BaseLabelProvider</code> implementation of this
* <code>IBaseLabelProvider</code> method clears its internal listener list.
* Subclasses may extend but should call the super implementation.
*/
    @Override
    public void dispose() {
        clearListeners();
    }

    /**
* The <code>BaseLabelProvider</code> implementation of this
* <code>IBaseLabelProvider</code> method returns <code>true</code>. Subclasses may
* override.
*/
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
        removeListenerObject(listener);
    }

    /**
* Fires a label provider changed event to all registered listeners Only
* listeners registered at the time this method is called are notified.
*
* @param event
*            a label provider changed event
*
* @see ILabelProviderListener#labelProviderChanged
*/
    protected void fireLabelProviderChanged(final LabelProviderChangedEvent event) {
        Object[] listeners = getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            final ILabelProviderListener l = (ILabelProviderListener) listeners[i];
            SafeRunnable.run(new SafeRunnable() {

                @Override
                public void run() {
                    l.labelProviderChanged(event);
                }
            });
        }
    }
}

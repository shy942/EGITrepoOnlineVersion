/***/
package org.eclipse.ui.internal.preferences;

/**
* @since 3.1
*/
public abstract class AbstractPropertyListener implements IPropertyMapListener {

    @Override
    public void propertyChanged(String[] propertyIds) {
        update();
    }

    @Override
    public void listenerAttached() {
        update();
    }

    protected abstract void update();
}

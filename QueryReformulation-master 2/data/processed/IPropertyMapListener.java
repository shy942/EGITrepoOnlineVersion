/***/
package org.eclipse.ui.internal.preferences;

/**
* @since 3.1
*/
public interface IPropertyMapListener {

    public void propertyChanged(String[] propertyIds);

    public void listenerAttached();
}

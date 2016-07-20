/***/
package org.eclipse.jface.preference;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
* Provides labels for <code>IPreferenceNode</code> objects.
*
* @since 3.0
*/
public class PreferenceLabelProvider extends LabelProvider {

    /**
* @param element must be an instance of <code>IPreferenceNode</code>.
* @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
*/
    @Override
    public String getText(Object element) {
        return ((IPreferenceNode) element).getLabelText();
    }

    /**
* @param element must be an instance of <code>IPreferenceNode</code>.
* @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
*/
    @Override
    public Image getImage(Object element) {
        return ((IPreferenceNode) element).getLabelImage();
    }
}

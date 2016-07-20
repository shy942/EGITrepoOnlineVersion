/***/
package org.eclipse.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferencePage;

/**
* This interface is similar to {@link IWorkbenchPropertyPage} with the addition
* of support for multiple selection.
*
* @see IWorkbenchPropertyPage
* @since 3.7
*/
public interface IWorkbenchPropertyPageMulti extends IPreferencePage {

    /**
* Sets the elements that own properties shown on this page. This method
* will be called if the property page responds to multiple selection.
*
* @param elements
*            objects that own the properties shown in this page
*/
    public void setElements(IAdaptable[] elements);
}

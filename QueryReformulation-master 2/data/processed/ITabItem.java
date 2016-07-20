/***/
package org.eclipse.ui.views.properties.tabbed;

import org.eclipse.swt.graphics.Image;

/**
* Represents a tab to be displayed in the tab list in the tabbed property sheet
* page.
*
* @author Anthony Hunter
*/
public interface ITabItem {

    /**
* Get the icon image for the tab.
*
* @return the icon image for the tab.
*/
    public Image getImage();

    /**
* Get the text label for the tab.
*
* @return the text label for the tab.
*/
    public String getText();

    /**
* Determine if this tab is selected.
*
* @return <code>true</code> if this tab is selected.
*/
    public boolean isSelected();

    /**
* Determine if this tab is indented.
*
* @return <code>true</code> if this tab is indented.
*/
    public boolean isIndented();
}

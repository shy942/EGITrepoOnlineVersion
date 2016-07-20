/***/
package org.eclipse.ui.internal.menus;

import org.eclipse.ui.internal.ActionSetsEvent;

/**
* <p>
* A listener to changes in the list of active action sets.
* </p>
* <p>
* This class is only intended for internal use within
* <code>org.eclipse.ui.workbench</code>.
* </p>
* <p>
* This class will eventually exist in <code>org.eclipse.jface.menus</code>.
* </p>
*
* @since 3.2
*/
public interface IActionSetsListener {

    /**
* Indicates that the list of active action sets has changed.
*
* @param event
*            The event carrying information about the new state of the
*            action sets; never <code>null</code>.
*/
    public void actionSetsChanged(ActionSetsEvent event);
}

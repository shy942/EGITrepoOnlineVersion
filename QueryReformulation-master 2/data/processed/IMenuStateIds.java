/***/
package org.eclipse.jface.menus;

import org.eclipse.core.commands.INamedHandleStateIds;
import org.eclipse.jface.commands.RadioState;
import org.eclipse.jface.commands.ToggleState;

/**
* <p>
* State identifiers that should be understood by items and renderers of items.
* The state is associated with the command, and then interpreted by the menu
* renderer.
* </p>
* <p>
* Clients may implement or extend this class.
* </p>
*
* @since 3.2
*/
public interface IMenuStateIds extends INamedHandleStateIds {

    /**
* The state id used for indicating the widget style of a command presented
* in the menus and tool bars. This state must be an instance of
* {@link ToggleState} or {@link RadioState}.
*/
    //$NON-NLS-1$
    public static String STYLE = "STYLE";
}

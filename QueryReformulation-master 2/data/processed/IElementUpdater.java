/***/
package org.eclipse.ui.commands;

import java.util.Map;
import org.eclipse.ui.menus.UIElement;

/**
* An IHandler for a command that expects to provide feedback through the
* registered element mechanism must implement this interface.
*
* @since 3.3
*
*/
public interface IElementUpdater {

    /**
* Whenever the elements for a command are refreshed, this method is called
* on the active handler for that command.
* <p>
* <b>Note:</b> Handlers must never cache the element, which can disappear
* or be replaced at any time. Everybody should go through the
* ICommandService refreshElements(*) method.
* </p>
*
* @param element
*            An element for a specific UI element. Will not be
*            <code>null</code>.
* @param parameters
*            Any parameters registered with the callback. Will not be
*            <code>null</code>, but it may be empty.
*/
    public void updateElement(UIElement element, Map parameters);
}

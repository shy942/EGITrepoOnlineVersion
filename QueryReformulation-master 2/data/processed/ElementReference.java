/***/
package org.eclipse.ui.internal.commands;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.ui.commands.IElementReference;
import org.eclipse.ui.menus.UIElement;

/**
* Our element reference that is used during element
* registration/unregistration.
*
* @since 3.3
*/
public class ElementReference implements IElementReference {

    private String commandId;

    private UIElement element;

    private HashMap parameters;

    /**
* Construct the reference.
*
* @param id
*            command id. Must not be <code>null</code>.
* @param adapt
*            the element. Must not be <code>null</code>.
* @param parms.
*            parameters used for filtering. Must not be <code>null</code>.
*/
    public  ElementReference(String id, UIElement adapt, Map parms) {
        commandId = id;
        element = adapt;
        if (parms == null) {
            parameters = new HashMap();
        } else {
            parameters = new HashMap(parms);
        }
    }

    @Override
    public UIElement getElement() {
        return element;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    @Override
    public Map getParameters() {
        return parameters;
    }
}

/***/
package org.eclipse.ui.internal.keys.model;

import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.commands.contexts.Context;

/**
* @since 3.4
*
*/
public class ContextElement extends ModelElement {

    /**
* @param kc
*/
    public  ContextElement(KeyController kc) {
        super(kc);
    }

    public void init(Context context) {
        setId(context.getId());
        setModelObject(context);
        try {
            setName(context.getName());
            setDescription(context.getDescription());
        } catch (NotDefinedException e) {
        }
    }
}

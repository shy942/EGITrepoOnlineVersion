/***/
package org.eclipse.ui.internal.keys.model;

import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.bindings.Scheme;

/**
* @since 3.4
*
*/
public class SchemeElement extends ModelElement {

    /**
* @param kc
*/
    public  SchemeElement(KeyController kc) {
        super(kc);
    }

    /**
* @param scheme
*/
    public void init(Scheme scheme) {
        setId(scheme.getId());
        setModelObject(scheme);
        try {
            setName(scheme.getName());
            setDescription(scheme.getDescription());
        } catch (NotDefinedException e) {
            e.printStackTrace();
        }
    }
}

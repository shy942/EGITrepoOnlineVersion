/***/
package org.eclipse.ui.internal.keys.model;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.bindings.BindingManager;
import org.eclipse.jface.bindings.Scheme;

/**
* @since 3.4
*
*/
public class SchemeModel extends CommonModel {

    //$NON-NLS-1$
    public static final String PROP_SCHEMES = "schemes";

    private List schemes;

    /**
* @param kc
*/
    public  SchemeModel(KeyController kc) {
        super(kc);
    }

    /**
* @param bindingManager
*/
    public void init(BindingManager bindingManager) {
        schemes = new ArrayList();
        Scheme[] definedSchemes = bindingManager.getDefinedSchemes();
        for (int i = 0; i < definedSchemes.length; i++) {
            SchemeElement se = new SchemeElement(controller);
            se.init(definedSchemes[i]);
            se.setParent(this);
            schemes.add(se);
            if (definedSchemes[i] == bindingManager.getActiveScheme()) {
                setSelectedElement(se);
            }
        }
    }

    /**
* @return Returns the schemes.
*/
    public List getSchemes() {
        return schemes;
    }

    /**
* @param schemes
*            The schemes to set.
*/
    public void setSchemes(List schemes) {
        List old = this.schemes;
        this.schemes = schemes;
        controller.firePropertyChange(this, PROP_SCHEMES, old, schemes);
    }
}

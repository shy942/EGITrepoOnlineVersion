/***/
package org.eclipse.ui.tests.navigator.cdt;

import org.eclipse.core.resources.IResource;

// Corresponds to ICModel (workspace root)
public class CRoot extends CElement {

    public  CRoot(CNavigatorContentProvider cp, IResource resource) {
        super(cp, resource, null);
    }
}

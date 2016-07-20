/***/
package org.eclipse.ui.tests.views.properties.tabbed.override;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.tests.views.properties.tabbed.model.Error;
import org.eclipse.ui.tests.views.properties.tabbed.model.File;
import org.eclipse.ui.tests.views.properties.tabbed.model.Folder;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;
import org.eclipse.ui.tests.views.properties.tabbed.model.Warning;

/**
* The content provider for the override tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class OverrideTestsContentProvider implements IStructuredContentProvider {

    private Element[] elements;

    @Override
    public void dispose() {
    // not implemented
    }

    @Override
    public Object[] getElements(Object parent) {
        if (elements == null) {
            elements = new Element[] { //$NON-NLS-1$
            new Information("Information"), //$NON-NLS-1$ //$NON-NLS-2$
            new Warning("Warning"), //$NON-NLS-1$ //$NON-NLS-2$
            new Error("Error"), new File("File"), //$NON-NLS-1$//$NON-NLS-2$
            new Folder("Folder") };
        }
        return elements;
    }

    @Override
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    // not implemented
    }
}

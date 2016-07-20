/***/
package org.eclipse.ui.tests.propertysheet;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
* @since 3.4
*
*/
public class TestPropertySheetPage extends PropertySheetPage implements IPropertySheetPage, IAdapterFactory {

    private ISelection fSelection;

    private IWorkbenchPart fPart;

    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        // singleton cleanup
        fSelection = null;
        fPart = null;
        return this;
    }

    @Override
    public Class[] getAdapterList() {
        return new Class[] { IPropertySheetPage.class };
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        super.selectionChanged(part, selection);
        fPart = part;
        fSelection = selection;
    }

    /**
* @return Returns the selection.
*/
    public ISelection getSelection() {
        return fSelection;
    }

    /**
* @return Returns the part.
*/
    public IWorkbenchPart getPart() {
        return fPart;
    }
}

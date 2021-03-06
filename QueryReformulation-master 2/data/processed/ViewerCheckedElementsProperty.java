/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.property.set.DelegatingSetProperty;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.IViewerSetProperty;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
* @since 3.3
*
*/
public class ViewerCheckedElementsProperty extends DelegatingSetProperty implements IViewerSetProperty {

    ISetProperty checkable;

    ISetProperty checkboxTableViewer;

    ISetProperty checkboxTreeViewer;

    /**
* @param elementType
*/
    public  ViewerCheckedElementsProperty(Object elementType) {
        super(elementType);
        checkable = new CheckableCheckedElementsProperty(elementType);
        checkboxTableViewer = new CheckboxTableViewerCheckedElementsProperty(elementType);
        checkboxTreeViewer = new CheckboxTreeViewerCheckedElementsProperty(elementType);
    }

    @Override
    protected ISetProperty doGetDelegate(Object source) {
        if (source instanceof CheckboxTableViewer)
            return checkboxTableViewer;
        if (source instanceof CheckboxTreeViewer)
            return checkboxTreeViewer;
        return checkable;
    }

    @Override
    public IViewerObservableSet observe(Viewer viewer) {
        return (IViewerObservableSet) observe(DisplayRealm.getRealm(viewer.getControl().getDisplay()), viewer);
    }
}

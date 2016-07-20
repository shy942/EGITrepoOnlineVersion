/***/
package org.eclipse.jface.internal.databinding.viewers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.jface.databinding.viewers.ViewerSetProperty;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
* @since 3.3
*
*/
public class StructuredViewerFiltersProperty extends ViewerSetProperty {

    @Override
    public Object getElementType() {
        return ViewerFilter.class;
    }

    @Override
    protected Set doGetSet(Object source) {
        return new HashSet(Arrays.asList(((StructuredViewer) source).getFilters()));
    }

    @Override
    public void doSetSet(Object source, Set set, SetDiff diff) {
        doSetSet(source, set);
    }

    @Override
    protected void doSetSet(Object source, Set set) {
        StructuredViewer viewer = (StructuredViewer) source;
        viewer.getControl().setRedraw(false);
        try {
            viewer.setFilters((ViewerFilter[]) set.toArray(new ViewerFilter[set.size()]));
        } finally {
            viewer.getControl().setRedraw(true);
        }
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return null;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "StructuredViewer.filters{} <ViewerFilter>";
    }
}

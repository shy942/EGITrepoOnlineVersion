/***/
package org.eclipse.ui.internal.navigator.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
*
* A no-op viewer filter used to prevent null return values from
* {@link CommonFilterDescriptor#createFilter()}.
*
* @since 3.2
*
*/
public class SkeletonViewerFilter extends ViewerFilter {

    /**
* The singleton instance.
*/
    public static final SkeletonViewerFilter INSTANCE = new SkeletonViewerFilter();

    private  SkeletonViewerFilter() {
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        return true;
    }
}

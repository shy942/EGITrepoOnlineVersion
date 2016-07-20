/***/
package org.eclipse.ui.internal.navigator.sorters;

import org.eclipse.jface.viewers.ViewerSorter;

/**
* @since 3.2
*
*/
public class SkeletonViewerSorter extends ViewerSorter {

    /** The singleton instance. */
    public static final ViewerSorter INSTANCE = new SkeletonViewerSorter();

    private  SkeletonViewerSorter() {
    }
}

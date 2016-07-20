/***/
package org.eclipse.ui.internal.navigator.workingsets;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
* @since 3.2
*
*/
public class WorkingSetSorter extends ViewerSorter {

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (viewer instanceof StructuredViewer) {
            ILabelProvider labelProvider = (ILabelProvider) ((StructuredViewer) viewer).getLabelProvider();
            String text1 = labelProvider.getText(e1);
            String text2 = labelProvider.getText(e2);
            if (text1 != null) {
                return text1.compareTo(text2);
            }
        }
        return -1;
    }
}

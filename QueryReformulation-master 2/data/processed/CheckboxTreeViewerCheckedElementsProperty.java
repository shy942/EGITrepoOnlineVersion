/***/
package org.eclipse.jface.internal.databinding.viewers;

import java.util.Arrays;
import java.util.Set;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.jface.viewers.CheckboxTreeViewer;

/**
* @since 3.3
*
*/
public class CheckboxTreeViewerCheckedElementsProperty extends CheckboxViewerCheckedElementsProperty {

    /**
* @param elementType
*/
    public  CheckboxTreeViewerCheckedElementsProperty(Object elementType) {
        super(elementType);
    }

    @Override
    protected Set doGetSet(Object source) {
        CheckboxTreeViewer viewer = (CheckboxTreeViewer) source;
        Set set = createElementSet(viewer);
        set.addAll(Arrays.asList(viewer.getCheckedElements()));
        return set;
    }

    @Override
    protected void doSetSet(Object source, Set set, SetDiff diff) {
        doSetSet(source, set);
    }

    @Override
    protected void doSetSet(Object source, Set set) {
        CheckboxTreeViewer viewer = (CheckboxTreeViewer) source;
        viewer.setCheckedElements(set.toArray());
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        String s = "CheckboxTreeViewer.checkedElements{}";
        if (getElementType() != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += " <" + getElementType() + ">";
        return s;
    }
}

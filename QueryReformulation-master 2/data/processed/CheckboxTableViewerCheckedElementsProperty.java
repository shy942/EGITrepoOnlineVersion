/***/
package org.eclipse.jface.internal.databinding.viewers;

import java.util.Arrays;
import java.util.Set;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.jface.viewers.CheckboxTableViewer;

/**
* @since 3.3
*
*/
public class CheckboxTableViewerCheckedElementsProperty extends CheckboxViewerCheckedElementsProperty {

    /**
* @param elementType
*/
    public  CheckboxTableViewerCheckedElementsProperty(Object elementType) {
        super(elementType);
    }

    @Override
    protected Set doGetSet(Object source) {
        CheckboxTableViewer viewer = (CheckboxTableViewer) source;
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
        CheckboxTableViewer viewer = (CheckboxTableViewer) source;
        viewer.setCheckedElements(set.toArray());
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        String s = "CheckboxTableViewer.checkedElements{}";
        if (getElementType() != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += " <" + getElementType() + ">";
        return s;
    }
}

/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.ui.internal.WorkbenchMessages;

/**
* This is used to sort views in a ShowViewDialog.
*/
public class ViewComparator extends ViewerComparator {

    //$NON-NLS-1$
    private static final String EMPTY_STRING = "";

    /**
* Returns a negative, zero, or positive number depending on whether the
* first element is less than, equal to, or greater than the second element.
*/
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        // place "General" category first
        if (WorkbenchMessages.ICategory_other.equals(e1)) {
            return -1;
        }
        if (WorkbenchMessages.ICategory_general.equals(e2)) {
            return 1;
        }
        String str1;
        if (e1 instanceof MPartDescriptor) {
            str1 = ((MPartDescriptor) e1).getLocalizedLabel();
        } else {
            str1 = e1.toString();
        }
        String str2;
        if (e2 instanceof MPartDescriptor) {
            str2 = ((MPartDescriptor) e2).getLocalizedLabel();
        } else {
            str2 = e2.toString();
        }
        if (str1 == null) {
            str1 = EMPTY_STRING;
        }
        if (str2 == null) {
            str2 = EMPTY_STRING;
        }
        String s1 = DialogUtil.removeAccel(str1);
        String s2 = DialogUtil.removeAccel(str2);
        return getComparator().compare(s1, s2);
    }
}

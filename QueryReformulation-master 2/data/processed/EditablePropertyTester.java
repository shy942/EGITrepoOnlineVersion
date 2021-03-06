/***/
package org.eclipse.ui.internal.views.markers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IMarker;

/**
* EditablePropertyTester is a property tester for the editable property of the
* selected marker.
*
* @since 3.4
*
*/
public class EditablePropertyTester extends PropertyTester {

    //$NON-NLS-1$
    private static final Object EDITABLE = "editable";

    /**
* Create a new instance of the receiver.
*/
    public  EditablePropertyTester() {
        super();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (property.equals(EDITABLE)) {
            MarkerSupportItem item = (MarkerSupportItem) receiver;
            Set<IMarker> markers = new HashSet();
            if (item.isConcrete()) {
                markers.add(((MarkerEntry) receiver).getMarker());
            } else {
                MarkerSupportItem[] children = item.getChildren();
                for (int i = 0; i < children.length; i++) {
                    if (children[i].isConcrete()) {
                        markers.add(((MarkerEntry) children[i]).getMarker());
                    }
                }
            }
            if (!markers.isEmpty()) {
                Iterator<IMarker> elements = markers.iterator();
                while (elements.hasNext()) {
                    IMarker marker = elements.next();
                    if (!marker.getAttribute(IMarker.USER_EDITABLE, true)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}

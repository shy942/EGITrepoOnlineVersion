/***/
package org.eclipse.ui.tests.navigator.extension;

import java.text.Collator;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TestSorterResource extends ViewerSorter {

    public static String _sorterProperty;

    public static Object _sorterElement;

    public boolean _forward = true;

    public static void resetTest() {
        _sorterProperty = null;
        _sorterElement = null;
    }

    public  TestSorterResource() {
        super();
    }

    public  TestSorterResource(Collator collator) {
        super(collator);
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof IResource) {
            if (e2 instanceof IResource) {
                IResource lvalue = (IResource) e1;
                IResource rvalue = (IResource) e2;
                if (_forward)
                    return lvalue.getName().compareTo(rvalue.getName());
                return rvalue.getName().compareTo(lvalue.getName());
            }
            return -1;
        } else if (e2 instanceof IResource) {
            return +1;
        }
        return super.compare(viewer, e1, e2);
    }

    @Override
    public boolean isSorterProperty(Object element, String property) {
        _sorterProperty = property;
        _sorterElement = element;
        return false;
    }
}
